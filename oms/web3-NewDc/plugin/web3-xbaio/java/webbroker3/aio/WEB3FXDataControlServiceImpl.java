head	1.3;
access;
symbols;
locks; strict;
comment	@// @;


1.3
date	2011.03.30.08.23.40;	author zhang-tengyu;	state Exp;
branches;
next	1.2;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1944d92e88c56d7;
filename	WEB3FXDataControlServiceImpl.java;

1.2
date	2011.03.28.06.22.41;	author zhang-tengyu;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	3f04d9029305df6;
filename	WEB3FXDataControlServiceImpl.java;

1.1
date	2011.03.16.02.27.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXDataControlServiceImpl.java;


desc
@@


1.3
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�f�[�^����T�[�r�XImpl(WEB3FXDataControlServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 ���E (���u) �V�K�쐬
                 : 2006/02/08 �A���� (���u) �d�l�ύX�E���f��469�A471
                                          �d�l�ύX�EDB�X�V�d�l 071�A072�A073�A074
                 : 2006/02/24 ���_�O (���u) �d�l�ύX�E���f��446�A459�A465�A502
                 : 2006/04/25 �юu�� (���u) �d�l�ύX�E���f��534�A536
                                          �d�l�ύX�EDB�X�V�d�l 081
                 : 2006/05/08 ���� (���u) �d�l�ύX�E���f��550  
                 : 2006/05/10 ���� (���u) �d�l�ύX�E���f��549�A557�A559
                 : 2006/05/12 �s�p (���u) �d�l�ύX�E���f��571 
                 : 2006/05/15 �s�p (���u) �d�l�ύX�EDB�X�V�d�l 086�A087�A088 
                 : 2006/05/16 ���� (���u) �d�l�ύX�E���f��583            
                 : 2006/06/05 ��� (SCS) �d�l�ύX No.590�EDB�X�V�d�l 091
                 : 2006/07/12 ��O�� (���u) �d�l�ύX ���f��598�EDB�X�V�d�l092�A094
                 : 2006/08/04 ��� (SCS) �d�l�ύX No.609�EDB�X�V�d�l 096,097
                 : 2006/08/23 ��� (SCS) �d�l�ύX�E���f��631
                 : 2006/10/16 �����q (���u) �d�l�ύX�E���f��657
Revesion History : 2008/04/08 ���n�m (���u) �d�l�ύX�E���f��838,841,843
                 : 2008/05/08 ���� (SCS) �d�l�ύX�E���f��838
                 : 2008/05/23 �O�� (SCS) 
Revesion History : 2008/05/20 �đo�g (���u) �d�l�ύX�E���f��852,856,867,872,873,875
�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@DB�X�V�d�l161,162,163,164,165,166,167,168,169
Revesion History : 2008/06/17 �đo�g (���u) �d�l�ύX�E���f��898 DB�X�V�d�l171,172
Revesion History : 2008/06/23 �đo�g (���u) �d�l�ύX�E���f��904,905,906
Revesion History : 2008/07/02 �g�C�� (���u) �d�l�ύX�E���f��908
Revesion History : 2008/09/08 ���u�� (���u) �d�l�ύX�E���f��973,981,983
Revesion History : 2008/10/02 �|�� (SCS) �d�l�ύX�E���f��1046
Revesion History : 2008/10/07 ���u�� (���u) �d�l�ύX�E���f��990,1002,1010,1021,1025,1026
                                                      1027,1028,1029,1030,1031,1032
                                                      1038,1050,1053,1055,1060,1062
                                                      1065,1066,1072,1073,1076
                                       �c�a�X�V�d�l�E���f��178,179,181,182,183,184,185
                                                      186,187189,190,191,192,193
                                                      195,196,197,199,200
Revesion History : 2008/10/28 �哈 (SCS) �d�l�ύX�E���f��
Revesion History : 2008/11/14 ���� (SCS) �d�l�ύX�E���f��1084
Revesion History : 2008/12/16 �哈 (SCS) �d�l�ύX�E���f��1088,1089
Revesion History : 2009/01/22 �g�� (SCS) �d�l�ύX�E���f��1092,1093,1094
Revesion History : 2009/03/12 �đo�g (���u) �d�l�ύX�E���f��1108,1150 �c�a�X�V�d�l208
Revesion History : 2009/03/19 �Ԑi (���u) �d�l�ύX�E���f��1124�A1135�A1136�A1157
                                         �c�a�X�V�d�l214�A220�A223
Revesion History : 2009/04/20 �Ԑi (���u) �d�l�ύX�E���f��1161
Revesion History : 2009/04/22 �Ԑi (���u) �c�a�X�V�d�l228
Revesion History : 2009/05/31 �đo�g (���u) �c�a�X�V�d�l229
Revesion History : 2009/06/26 ���g (���u) �d�l�ύX�E���f��1171
Revesion History : 2009/08/25 �����F (���u) �d�l�ύX�E���f��1194
Revesion History : 2009/09/16 �И��� (���u) �d�l�ύX�E���f��1214
*/
package webbroker3.aio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.SocketTimeoutException;
import java.rmi.RemoteException;
import java.security.KeyManagementException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.net.ssl.SSLSocketFactory;
import javax.xml.namespace.QName;
import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;


import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.gftforex.soap_api.ResultInfoAddAccount;
import com.gftforex.soap_api.ResultInfoCreateUser;
import com.sun.xml.internal.ws.client.BindingProviderProperties;
import com.sun.xml.internal.ws.developer.JAXWSProperties;

import fx.client.EntryReceiptIn;
import fx.client.EntryReceiptOut;
import fx.client.WebService;
import fx.client.WebServiceSoap;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.CompFxConditionRow;
import webbroker3.aio.data.FxAccountCodeParams;
import webbroker3.aio.data.FxAccountCodeRow;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.data.FxAccountRow;
import webbroker3.aio.data.FxTransferMasterDao;
import webbroker3.aio.data.FxTransferMasterParams;
import webbroker3.aio.data.FxTransferMasterRow;
import webbroker3.aio.data.FxUnnecessaryExplanationParams;
import webbroker3.aio.data.FxUnnecessaryExplanationRow;
import webbroker3.aio.data.GftAccountOpenStatusParams;
import webbroker3.aio.data.GftAccountOpenStatusRow;
import webbroker3.aio.data.GftMessageParams;
import webbroker3.aio.data.GftMessageRow;
import webbroker3.aio.data.GftTransferStatusParams;
import webbroker3.aio.data.GftTransferStatusRow;
import webbroker3.aio.define.WEB3AdminAioGftOperationDivDef;
import webbroker3.aio.define.WEB3AdminFXUploadNoteOneDef;
import webbroker3.aio.define.WEB3AioAcceptResultCodeDef;
import webbroker3.aio.define.WEB3AioCashInOutAmountDivDef;
import webbroker3.aio.define.WEB3AioFxAccountOpenDivDef;
import webbroker3.aio.define.WEB3AioQuestionAnswerDef;
import webbroker3.aio.define.WEB3KamokuCdDef;
import webbroker3.aio.handler.WEB3FXSOAPMsgHandler;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.aio.message.WEB3FXTradeAgreementUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountOpenStatusDivDef;
import webbroker3.common.define.WEB3AioAccountDivDef;
import webbroker3.common.define.WEB3AccountOpenDef;
import webbroker3.common.define.WEB3DeliveryDateDivDef;
import webbroker3.common.define.WEB3EffectiveDivDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ExtConnectSystemCodeDef;
import webbroker3.common.define.WEB3FxSystemDivDef;
import webbroker3.common.define.WEB3FxTransStatusOperationDivDef;
import webbroker3.common.define.WEB3GftErrorReasonCodeDef;
import webbroker3.common.define.WEB3GftMessageDivDef;
import webbroker3.common.define.WEB3GftMessageOperationDef;
import webbroker3.common.define.WEB3GftTransStatusCourseDivDef;
import webbroker3.common.define.WEB3InstitutionPreferencesNameDef;
import webbroker3.common.define.WEB3MrfAccOpenDivDef;
import webbroker3.common.define.WEB3QuestionDivDef;
import webbroker3.common.define.WEB3SendRcvDivDef;
import webbroker3.common.define.WEB3SoapResultCodeDef;
import webbroker3.common.define.WEB3TransferStatusDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.gentrade.data.InstitutionPreferencesDao;
import webbroker3.gentrade.data.InstitutionPreferencesRow;
import webbroker3.gentrade.data.QuestionAnswerParams;
import webbroker3.gentrade.data.QuestionAnswerRow;
import webbroker3.gentrade.data.QuestionParams;
import webbroker3.gentrade.data.QuestionRow;
import webbroker3.gentrade.data.SoapConnectPrefRpcParams;
import webbroker3.gentrade.data.SoapMessageParams;
import webbroker3.gentrade.define.WEB3GentradeBatoCheckResultDef;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (FX�f�[�^����T�[�r�XImpl) <BR>
 * FX�f�[�^����T�[�r�X�����N���X�B <BR>
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3FXDataControlServiceImpl implements WEB3FXDataControlService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXDataControlServiceImpl.class);
    
    private SSLSocketFactory sslSocketFactory;
    
    /**
     * @@roseuid 41E767E400DA
     */
    public WEB3FXDataControlServiceImpl()
    {
    }

    /**
     * (getFX�ڋq) <BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�AFX�V�X�e���R�[�h�A�ڋq�R�[�h�� <BR>
     *      �Y������FX�ڋqPa rams���擾����B <BR>
     * <BR>
     * �P�jDB���� <BR>
     * FX�ڋq�e�[�u��(fx_account)���ȉ��̏����Ō�������B <BR>
     * <BR>
     * [����] <BR>
     * �،���ЃR�[�h = ����.�،���ЃR�[�h <BR>
     * ���X�R�[�h = ����.���X�R�[�h <BR>
     * FX�V�X�e���R�[�h = ����.FX�V�X�e���R�[�h <BR>
     * �ڋq�R�[�h = ����.�ڋq�R�[�h <BR>
     * <BR>
     * �Q�j�������ʂ�FX�ڋqParams��ԋp����B <BR>
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strFxSystemCode - FX�V�X�e���R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@return webbroker3.aio.data.FxAccountParams
     * @@throws WEB3BaseException
     * @@throws NotFoundException
     * @@roseuid 41C0F587002B
     */
    public FxAccountParams getFXAccount(String l_strInstitutionCode,
        String l_strBranchCode, String l_strFxSystemCode,
        String l_strAccountCode) throws WEB3BaseException, NotFoundException 
    {
        String STR_METHOD_NAME ="getFXAccount(String l_strInstitutionCode, String l_strBranchCode, " +
            "String l_strFxSystemCode, String l_strAccountCode)";
        log.entering(STR_METHOD_NAME);
        
        //�P�jDB����                       
        //�@@FX�ڋq�e�[�u��(fx_account)���ȉ��̏����Ō�������B 
        //�@@[����]                         
        //�@@�،���ЃR�[�h = ����.�،���ЃR�[�h 
        //�@@���X�R�[�h = ����.���X�R�[�h   
        //�@@FX�V�X�e���R�[�h = ����.FX�V�X�e���R�[�h 
        //�@@�ڋq�R�[�h = ����.�ڋq�R�[�h
        
        String l_strWhere = null;
        if (l_strAccountCode != null && l_strAccountCode.length() == 6)
        {
            l_strWhere = "institution_code = ? and branch_code = ?" +
                " and fx_system_code = ? and substr(account_code, 0, 6) = ? ";
        }
        else
        {
            l_strWhere = "institution_code = ? and branch_code = ?" +
                " and fx_system_code = ? and account_code = ? ";
        }
        
        Object[] l_objValue = {l_strInstitutionCode, l_strBranchCode, l_strFxSystemCode,
            l_strAccountCode};
        
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    FxAccountRow.TYPE,
                    l_strWhere,
                    null,
                    l_objValue);
        }
        catch (DataException l_ex)
        {
            log.debug("__DataException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //�Q�j�������ʂ�FX�ڋqParams��ԋp����B
        if(l_lisRows.size() == 0)
        {
            throw new NotFoundException("FX�ڋq�e�[�u�����擾�ł��܂���ł���");
        }
        
        if(l_lisRows.size() > 1)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "FX�ڋq�e�[�u���ɕ�����");
        }
        
        
        FxAccountParams l_fxAccountParams = (FxAccountParams)l_lisRows.get(0);
        
        log.exiting(STR_METHOD_NAME);
        return l_fxAccountParams;
    }

    /**
     * (getFX�ڋq) <BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�ɊY������FX�ڋqParams���擾����B <BR>
     * �P�jFX�V�X�e���R�[�h�擾 <BR>
     * this.get��Е�FX�V�X�e������()���R�[������B <BR>
     * <BR>
     * [�����̐ݒ�] <BR>
     * �،���ЃR�[�h�F ����.�،���ЃR�[�h <BR>
     * ���X�R�[�h�F ����.���X�R�[�h <BR>
     * <BR>
     * FX�V�X�e���R�[�h = get��Е�FX�V�X�e������()�̖߂�l.FX�V�X�e���R�[�h <BR>
     * <BR>
     * �Q�jDB���� <BR>
     * FX�ڋq�e�[�u��(fx_account)���ȉ��̏����Ō�������B <BR>
     * <BR>
     * [����] <BR>
     * �،���ЃR�[�h = ����.�،���ЃR�[�h <BR>
     * ���X�R�[�h = ����.���X�R�[�h <BR>
     * FX�V�X�e���R�[�h = �P�j�ɂĎ擾����FX�V�X�e���R�[�h <BR>
     * �ڋq�R�[�h = ����.�ڋq�R�[�h <BR>
     * <BR>�� �ڋq�R�[�h <BR>
     * �i����.�ڋq�R�[�h.length() == 6�j�̏ꍇ�́A�ŏ���6byte�̂ݔ�r����B) <BR>
     * <BR>
     * �R�j�������ʂ�ԋp����B <BR>
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@return webbroker3.aio.data.FxAccountParams
     * @@throws WEB3BaseException
     * @@throws NotFoundException
     * @@roseuid 41BD87A00335
     */
    public FxAccountParams getFXAccount(String l_strInstitutionCode,
        String l_strBranchCode, String l_strAccountCode) 
        throws WEB3BaseException, NotFoundException 
    {
        String STR_METHOD_NAME ="getFXAccount(String l_strInstitutionCode, " +
            "String l_strBranchCode, String l_strAccountCode)";
        log.entering(STR_METHOD_NAME);
        
        if(l_strAccountCode == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�jFX�V�X�e���R�[�h�擾 
        //�@@this.get��Е�FX�V�X�e������()���R�[������B 
        //�@@[�����̐ݒ�] 
        //�@@�،���ЃR�[�h�F�@@����.�،���ЃR�[�h 
        //�@@���X�R�[�h�F�@@����.���X�R�[�h 
        CompFxConditionParams l_compFxConditionParams = null;
        try
        {
            l_compFxConditionParams = 
                this.getCompFxCondition(l_strInstitutionCode, l_strBranchCode);
            
        }
        catch(NotFoundException l_ex)
        {
            log.debug("__NotFoundException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex); 
        }
        
        //�@@FX�V�X�e���R�[�h = get��Е�FX�V�X�e������()�̖߂�l.FX�V�X�e���R�[�h
        String l_strFxSystemCode = l_compFxConditionParams.getFxSystemCode();
        
        //�Q�jDB���� 
        //�@@FX�ڋq�e�[�u��(fx_account)���ȉ��̏����Ō�������B
        //�@@[����] 
        //�@@�،���ЃR�[�h = ����.�،���ЃR�[�h 
        //�@@���X�R�[�h = ����.���X�R�[�h 
        //�@@FX�V�X�e���R�[�h = �P�j�ɂĎ擾����FX�V�X�e���R�[�h 
        //�@@�ڋq�R�[�h = ����.�ڋq�R�[�h
        //���@@�ڋq�R�[�h 
        //�@@�@@�i����.�ڋq�R�[�h.length() == 6�j�̏ꍇ�́A�ŏ���6byte�̂ݔ�r����B)
        String l_strWhere = null;
        if(l_strAccountCode.length() == 6)
        {
            l_strWhere = "institution_code = ? and branch_code = ?" +
                " and fx_system_code = ? and substr(account_code ,0 ,6) = ? ";
        }
        else
        {
            l_strWhere = "institution_code = ? and branch_code = ?" +
                " and fx_system_code = ? and account_code = ? ";
        }
        
        Object[] l_objValue = {l_strInstitutionCode, l_strBranchCode, l_strFxSystemCode,
            l_strAccountCode};
        
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    FxAccountRow.TYPE,
                    l_strWhere,
                    null,
                    l_objValue);
        }
        catch (DataException l_ex)
        {
            log.debug("__DataException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        if(l_lisRows.size() == 0)
        {
            throw new NotFoundException("FX�ڋq�e�[�u�����擾�ł��܂���ł���");
        }
        
        if(l_lisRows.size() > 1)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "FX�ڋq�e�[�u���ɕ�����");
        }
        
        //�R�j�������ʂ�ԋp����B        
        FxAccountParams l_fxAccountParams = (FxAccountParams)l_lisRows.get(0);
        
        log.exiting(STR_METHOD_NAME);
        return l_fxAccountParams;
    }

    /**
     * (getFX�ڋq) <BR>
     * �����̏����ɊY������FX�ڋqParams�̈ꗗ��ԋp����B <BR>
     * <BR>
     * �P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B <BR>
     * <BR>
     * [doFindAllQuery()�ɃZ�b�g����p�����[�^] <BR>
     * rowType�F FX�ڋqRow.TYPE <BR>
     * where�F �p�����[�^.�������������� <BR>
     * orderBy�F �p�����[�^.�\�[�g���� <BR>
     * conditions�F null <BR>
     * bindVars�F �p�����[�^.���������f�[�^�R���e�i <BR>
     * <BR>
     * �������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B <BR>
     * <BR>
     * �Q�j�������ʂ�ԋp����B <BR>
     * 
     * @@param l_strQueryString - ��������������
     * @@param l_queryContainer - ���������f�[�^�R���e�i
     * @@param l_strSortCond - �\�[�g����
     * @@return webbroker3.aio.data.FxAccountParams[]
     * @@throws WEB3BaseException
     * @@roseuid 41BECC190291
     */
    public FxAccountParams[] getFXAccounts(String l_strQueryString,
        String[] l_queryContainer, String l_strSortCond) throws WEB3BaseException
    {
        String STR_METHOD_NAME ="getFXAccounts(String l_strQueryString, " +
            "String[] l_queryContainer, String l_strSortCond)";
        log.entering(STR_METHOD_NAME);
        
        //�P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B
        //�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^] 
        //�@@�@@rowType�F�@@FX�ڋqRow.TYPE 
        //�@@�@@where�F�@@�p�����[�^.�������������� 
        //�@@�@@orderBy�F�@@�p�����[�^.�\�[�g���� 
        //�@@�@@conditions�F�@@null 
        //�@@�@@bindVars�F�@@�p�����[�^.���������f�[�^�R���e�i 
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    FxAccountRow.TYPE,
                    l_strQueryString,
                    l_strSortCond,
                    null,
                    l_queryContainer);
        }
        catch (DataException l_ex)
        {
            log.debug("__DataException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //�@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B 
        if(l_lisRows.size() == 0)
        {
            return null;
        }
        
        //�Q�j�������ʂ�ԋp����B        
        FxAccountParams[] l_fxAccountParams = new FxAccountParams[l_lisRows.size()]; 
        l_lisRows.toArray(l_fxAccountParams);

        log.exiting(STR_METHOD_NAME);
        return l_fxAccountParams;
    }

    /**
     * (getFX�����J�݋敪)<BR>
     * FX�����J�݋敪���擾����B<BR>
     * <BR>
     * �P�j����.�X�V������J�ݏ󋵋敪�̒l�ɂ��A�ȉ���FX�����J�݋敪��߂��B<BR>
     * <BR>
     * �E����.�X�V������J�ݏ󋵋敪��"�J�ݍ�"�̏ꍇ<BR>
     * <BR>
     * �@@"1:�J�ݍ�"<BR>
     * �E����.�X�V������J�ݏ󋵋敪��"����"�̏ꍇ<BR>
     * �@@"2:����"<BR>
     * �E����.�X�V������J�ݏ󋵋敪��"�U�֒�~"�̏ꍇ<BR>
     * �@@"3:�U�֒�~"<BR>
     * <BR>
     * �Q�jFX�����J�݋敪��ԋp����B<BR>
     * <BR>
     * @@param l_strUpdatedAccOpenDiv - (�X�V������J�ݏ󋵋敪)<BR>
     * �X�V������J�ݏ󋵋敪<BR>
     * @@return String
     * @@roseuid 41BECC190291
     */
    public String getFXAccountOpenDiv(String l_strUpdatedAccOpenDiv)
    {
        String STR_METHOD_NAME ="getFXAccountOpenDiv(String)";
        log.entering(STR_METHOD_NAME);

        String l_strFXAccOpenDiv = null;
        //����.�X�V������J�ݏ󋵋敪��"�J�ݍ�"�̏ꍇ
        //"1:�J�ݍ�"
        if (WEB3AioAccountDivDef.OPEN_COMPLETE.equals(l_strUpdatedAccOpenDiv))
        {
        	l_strFXAccOpenDiv = WEB3AccountOpenDef.OPEN;
        }
        //����.�X�V������J�ݏ󋵋敪��"����"�̏ꍇ
        //"2:����"
        else if (WEB3AioAccountDivDef.DELETE.equals(l_strUpdatedAccOpenDiv))
        {
        	l_strFXAccOpenDiv = WEB3AccountOpenDef.DELETED;
        }
        //����.�X�V������J�ݏ󋵋敪��"�U�֒�~"�̏ꍇ
        //"3:�U�֒�~"
        else if (WEB3AioAccountDivDef.TRANSFER_STOP.equals(l_strUpdatedAccOpenDiv))
        {
        	l_strFXAccOpenDiv = WEB3AccountOpenDef.TRANSFER_STOP;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strFXAccOpenDiv;
    }

    /**
     * (getFX�����ԍ�) <BR>
     * �����̏����ɊY������FX�����ԍ����擾����B <BR>
     * <BR>
     * �P�jFX�V�X�e���R�[�h�擾 <BR>
     * this.get��Е�FX�V�X�e������()���R�[������B <BR>
     * <BR>
     * [�����̐ݒ�] <BR>
     * �،���ЃR�[�h�F ����.�،���ЃR�[�h <BR>
     * ���X�R�[�h�F ����.���X�R�[�h <BR>
     * <BR>
     * FX�V�X�e���R�[�h = get��Е�FX�V�X�e������()�̖߂�l.FX�V�X�e���R�[�h <BR>
     * <BR>
     * �Q�jDB���� <BR>
     * FX�����ԍ��e�[�u��(fx_account_code)�� <BR>
     * �ȉ��̏����Ō�������B <BR>
     * <BR>
     * [����] <BR>
     * �،���ЃR�[�h = ����.�،���ЃR�[�h <BR>
     * ���X�R�[�h = ����.���X�R�[�h <BR>
     * FX�V�X�e���R�[�h = �P�j�ɂĎ擾����FX�V�X�e���R�[�h <BR>
     * �ڋq�R�[�h = ����.�ڋq�R�[�h <BR>
     * FX�R�[�X�敪 = ����.�R�[�X�敪 <BR>
     * <BR>�� �ڋq�R�[�h <BR>
     * �i����.�ڋq�R�[�h.length() == 6�j�̏ꍇ�́A�ŏ���6byte�̂ݔ�r����B) <BR>
     * <BR>
     * �R�j�������ʂ�ԋp����B <BR>
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@param l_strCourseDiv - �R�[�X�敪
     * 0�F DEFAULT 1�F 1���ʉ݃R�[�X 2�F 10���ʉ݃R�[�X
     * @@return webbroker3.aio.data.FxAccountCodeParams
     * @@throws WEB3BaseException
     * @@throws NotFoundException
     * @@roseuid 41C93457009D
     */
    public FxAccountCodeParams getFXAccountCode(String l_strInstitutionCode,
        String l_strBranchCode, String l_strAccountCode, String l_strCourseDiv)
        throws WEB3BaseException, NotFoundException  
    {
        
        String STR_METHOD_NAME ="getFXAccountCode(String l_strInstitutionCode, " +
            "String l_strBranchCode, String l_strAccountCode, String l_strCourseDiv)";
        log.entering(STR_METHOD_NAME);
        
        if(l_strAccountCode == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�jFX�V�X�e���R�[�h�擾 
        //�@@this.get��Е�FX�V�X�e������()���R�[������B
        //�@@[�����̐ݒ�] 
        //�@@�،���ЃR�[�h�F�@@����.�،���ЃR�[�h 
        //�@@���X�R�[�h�F�@@����.���X�R�[�h 
        CompFxConditionParams l_compFxConditionParams = null;
        try
        {
            l_compFxConditionParams = 
                this.getCompFxCondition(l_strInstitutionCode, l_strBranchCode);
            
        }
        catch(NotFoundException l_ex)
        {
            log.debug("__NotFoundException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex); 
        }
        
        //�@@FX�V�X�e���R�[�h = get��Е�FX�V�X�e������()�̖߂�l.FX�V�X�e���R�[�h 
        String l_strFxSystemCode = l_compFxConditionParams.getFxSystemCode();
        
        //�Q�jDB���� 
        //�@@FX�����ԍ��e�[�u��(fx_account_code)�� 
        //�@@�ȉ��̏����Ō�������B 
        //�@@[����] 
        //�@@�،���ЃR�[�h = ����.�،���ЃR�[�h 
        //�@@���X�R�[�h = ����.���X�R�[�h 
        //�@@FX�V�X�e���R�[�h = �P�j�ɂĎ擾����FX�V�X�e���R�[�h 
        //�@@�ڋq�R�[�h = ����.�ڋq�R�[�h 
        //�@@FX�R�[�X�敪 = ����.�R�[�X�敪
        //�@@���@@�ڋq�R�[�h 
        //�@@�@@�i����.�ڋq�R�[�h.length() == 6�j�̏ꍇ�́A�ŏ���6byte�̂ݔ�r����B)
        String l_strWhere = null;
        if(l_strAccountCode.length() == 6)
        {
            l_strWhere = "institution_code = ? and branch_code = ?" +
                " and fx_system_code = ? and substr(account_code ,0 ,6) = ? and fx_course_div = ?";
        }
        else
        {
            l_strWhere = "institution_code = ? and branch_code = ?" +
                " and fx_system_code = ? and account_code = ? and fx_course_div = ?";
        }
        
        Object[] l_objValue = {l_strInstitutionCode, l_strBranchCode, l_strFxSystemCode,
            l_strAccountCode, l_strCourseDiv};
        
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    FxAccountCodeRow.TYPE,
                    l_strWhere,
                    null,
                    l_objValue);
        }
        catch (DataException l_ex)
        {
            log.debug("__DataException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if(l_lisRows.size() == 0)
        {
            throw new NotFoundException("FX�����ԍ��e�[�u�����擾�ł��܂���ł���");
        }
        
        if(l_lisRows.size() > 1)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "FX�����ԍ��e�[�u���ɕ�����");
        }
        
        //�R�j�������ʂ�ԋp����B
        FxAccountCodeParams l_fxAccountCodeParams = (FxAccountCodeParams)l_lisRows.get(0);
        
        log.exiting(STR_METHOD_NAME);
        return l_fxAccountCodeParams;
    }

    /**
     * (getFX�����ԍ�) <BR>
     * �����̏����ɊY������FX�����ԍ��̈ꗗ���擾����B <BR>
     * <BR>
     * �P�jFX�V�X�e���R�[�h�擾 <BR>
     * this.get��Е�FX�V�X�e������()���R�[������B <BR>
     * <BR>
     * [�����̐ݒ�] <BR>
     * �،���ЃR�[�h�F ����.�،���ЃR�[�h <BR>
     * ���X�R�[�h�F ����.���X�R�[�h <BR>
     * <BR>
     * FX�V�X�e���R�[�h = get��Е�FX�V�X�e������()�̖߂�l.FX�V�X�e���R�[�h <BR>
     * <BR>
     * �Q�jDB���� <BR>
     * FX�����ԍ��e�[�u��(fx_account_code)���ȉ��̏����Ō�������B <BR>
     * <BR>
     * [����] <BR>
     * �،���ЃR�[�h = ����.�،���ЃR�[�h <BR>
     * ���X�R�[�h = ����.���X�R�[�h <BR>
     * FX�V�X�e���R�[�h = �P�j�ɂĎ擾����FX�V�X�e���R�[�h <BR>
     * �ڋq�R�[�h = ����.�ڋq�R�[�h <BR>
     * <BR>�� �ڋq�R�[�h <BR>
     * �i����.�ڋq�R�[�h.length() == 6�j�̏ꍇ�́A�ŏ���6byte�̂ݔ�r����B) <BR>
     * <BR>
     * �R�j�������ʂ�FX�����ԍ�Params�̔z���FX�R�[�X�敪�̏����� <BR>
     * �\�[�g���A�ԋp����B <BR>
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@return webbroker3.aio.data.FxAccountCodeParams[]
     * @@throws WEB3BaseException
     * @@roseuid 41C7EE70029A
     */
    public FxAccountCodeParams[] getFXAccountCodes(String l_strInstitutionCode,
        String l_strBranchCode, String l_strAccountCode) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME ="getFXAccountCodes(String l_strInstitutionCode, " +
            "String l_strBranchCode, String l_strAccountCode)";
        log.entering(STR_METHOD_NAME);
        
        if(l_strAccountCode == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�jFX�V�X�e���R�[�h�擾 
        //�@@this.get��Е�FX�V�X�e������()���R�[������B
        //�@@[�����̐ݒ�] 
        //�@@�،���ЃR�[�h�F�@@����.�،���ЃR�[�h 
        //�@@���X�R�[�h�F�@@����.���X�R�[�h
        CompFxConditionParams l_compFxConditionParams = null;
        try
        {
            l_compFxConditionParams = 
                this.getCompFxCondition(l_strInstitutionCode, l_strBranchCode);
            
        }
        catch(NotFoundException l_ex)
        {
            log.debug("__NotFoundExcepiton__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);    
        }
        
        //�@@FX�V�X�e���R�[�h = get��Е�FX�V�X�e������()�̖߂�l.FX�V�X�e���R�[�h 
        String l_strFxSystemCode = l_compFxConditionParams.getFxSystemCode();
        
        //�Q�jDB���� 
        //�@@FX�����ԍ��e�[�u��(fx_account_code)�� 
        //�@@�ȉ��̏����Ō�������B
        //�@@[����] 
        //�@@�،���ЃR�[�h = ����.�،���ЃR�[�h 
        //�@@���X�R�[�h = ����.���X�R�[�h 
        //�@@FX�V�X�e���R�[�h = �P�j�ɂĎ擾����FX�V�X�e���R�[�h 
        //�@@�ڋq�R�[�h = ����.�ڋq�R�[�h
        //�@@���@@�ڋq�R�[�h 
        //�@@�@@�i����.�ڋq�R�[�h.length() == 6�j�̏ꍇ�́A�ŏ���6byte�̂ݔ�r����B)
        String l_strWhere = null;
        if(l_strAccountCode.length() == 6)
        {
            l_strWhere = "institution_code = ? and branch_code = ?" +
                " and fx_system_code = ? and substr(account_code ,0 ,6) = ?";
        }
        else
        {
            l_strWhere = "institution_code = ? and branch_code = ?" +
                " and fx_system_code = ? and account_code = ?";
        }
        
        Object[] l_objValue = {l_strInstitutionCode, l_strBranchCode, l_strFxSystemCode,
            l_strAccountCode};

        //�R�j�������ʂ�FX�����ԍ�Params�̔z���FX�R�[�X�敪�̏����� 
        //�@@�\�[�g���A�ԋp����B
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    FxAccountCodeRow.TYPE,
                    l_strWhere,
                    "fx_course_div asc",
                    null,
                    l_objValue);
        }
        catch (DataException l_ex)
        {
            log.debug("__DataException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        FxAccountCodeParams[] l_fxAccountCodeParams = null;
        if(l_lisRows.size() != 0)
        {
            l_fxAccountCodeParams = 
                new FxAccountCodeParams[l_lisRows.size()]; 
            l_lisRows.toArray(l_fxAccountCodeParams);
            
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_fxAccountCodeParams;
    }

    /**
     * (getFX�����ԍ�)<BR>
     * �����̏����ɊY������FX�����ԍ��̈ꗗ���擾����B<BR>
     * <BR>
     * �P�jDB����<BR>
     * �@@FX�����ԍ��e�[�u��(fx_account_code)��<BR>
     * �@@�ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h = ����.���X�R�[�h<BR>
     * �@@FX�V�X�e���R�[�h = ����.FX�V�X�e���R�[�h<BR>
     * �@@�ڋq�R�[�h = ����.�ڋq�R�[�h<BR>
     * �@@FX�R�[�X�敪 = ����.�R�[�X�敪<BR>
     * <BR>
     * �@@���@@�ڋq�R�[�h<BR>
     * �@@�@@�i����.�ڋq�R�[�h.length() == 6�j�̏ꍇ�́A�ŏ���6byte�̂ݔ�r����B)<BR>
     * <BR>
     * �Q�j�������ʂ�ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * @@param l_strCourseDiv - (�R�[�X�敪)<BR>
     * �R�[�X�敪<BR>
     * @@param l_strFxSystemCode - (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h<BR>
     * @@return FxAccountCodeParams
     * @@throws WEB3BaseException, NotFoundException
     */
    public FxAccountCodeParams getFXAccountCode(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strCourseDiv,
        String l_strFxSystemCode) throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME = "getFXAccountCode(String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        if (l_strAccountCode == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //DB����
        //[����]
        //�@@�،���ЃR�[�h = ����.�،���ЃR�[�h
        StringBuffer l_sbSql = new StringBuffer();
        List l_lisSqlValues = new ArrayList();
        l_sbSql.append(" institution_code = ? ");
        l_lisSqlValues.add(l_strInstitutionCode);

        //�@@���X�R�[�h = ����.���X�R�[�h
        l_sbSql.append(" and branch_code = ? ");
        l_lisSqlValues.add(l_strBranchCode);

        //�@@FX�V�X�e���R�[�h = ����.FX�V�X�e���R�[�h
        l_sbSql.append(" and fx_system_code = ? ");
        l_lisSqlValues.add(l_strFxSystemCode);

        //�@@�ڋq�R�[�h = ����.�ڋq�R�[�h
        // �i����.�ڋq�R�[�h.length() == 6�j�̏ꍇ�́A�ŏ���6byte�̂ݔ�r����B) 
        if (l_strAccountCode.length() == 6)
        {
            l_sbSql.append(" and substr(account_code, 0, 6) = ? ");
        }
        else
        {
            l_sbSql.append(" and account_code = ? ");
        }
        l_lisSqlValues.add(l_strAccountCode);

        //�@@FX�R�[�X�敪 = ����.�R�[�X�敪
        l_sbSql.append(" and fx_course_div = ? ");
        l_lisSqlValues.add(l_strCourseDiv);

        List l_lisResults;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lisResults = l_processor.doFindAllQuery(
                FxAccountCodeRow.TYPE,
                l_sbSql.toString(),
                l_lisSqlValues.toArray());
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

        if (l_lisResults.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new NotFoundException("FX�����ԍ��e�[�u�����擾�ł��܂���ł���");
        }

        log.exiting(STR_METHOD_NAME);
        return new FxAccountCodeParams((FxAccountCodeRow)l_lisResults.get(0));
    }

    
    /**
     * (get��Е�FX�V�X�e������) <BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�Ɉ�v�����Е�FX�V�X�e������Params��ԋp����B <BR>
     * <BR>
     * �P�jDB���� <BR>
     * ��Е�FX�V�X�e�������e�[�u��(comp_fx_condition)���ȉ��̏����Ō�������B <BR>
     * <BR>
     * [����] <BR>
     * �،���ЃR�[�h = ����.�،���ЃR�[�h <BR>
     * ���X�R�[�h = ����.���X�R�[�h <BR>
     * <BR>
     * �Q�j�������ʂ̉�Е�FX�V�X�e������Params��ԋp����B <BR>
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@return webbroker3.aio.data.CompFxConditionParams
     * @@throws WEB3BaseException
     * @@throws NotFoundException
     * @@roseuid 41BD88400114
     */
    public CompFxConditionParams getCompFxCondition(
        String l_strInstitutionCode, String l_strBranchCode)
            throws WEB3BaseException, NotFoundException
    {
        String STR_METHOD_NAME ="getCompFxCondition( " +
            "String l_strInstitutionCode, String l_strBranchCode)";
        log.entering(STR_METHOD_NAME);
        
        //�P�jDB���� 
        //�@@��Е�FX�V�X�e�������e�[�u��(comp_fx_condition)���ȉ��̏����Ō�������B
        //�@@[����] 
        //�@@�،���ЃR�[�h = ����.�،���ЃR�[�h 
        //�@@���X�R�[�h = ����.���X�R�[�h 
        String l_strWhere = "institution_code = ? and branch_code = ? ";

    
        Object[] l_objValue = {l_strInstitutionCode, l_strBranchCode};
        
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    CompFxConditionRow.TYPE,
                    l_strWhere,
                    null,
                    l_objValue);
        }
        catch (DataException l_ex)
        {
            log.debug("__DataException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        if(l_lisRows.size() == 0)
        {
            throw new NotFoundException("��Е�FX�V�X�e�������e�[�u�����擾�ł��܂���ł���");
        }
        
        if(l_lisRows.size() > 1)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "��Е�FX�V�X�e�������e�[�u���ɕ�����");
        }

        //�Q�j�������ʂ̉�Е�FX�V�X�e������Params��ԋp����B
        CompFxConditionParams l_compFxConditionParams  = (CompFxConditionParams)l_lisRows.get(0);
        
        log.exiting(STR_METHOD_NAME);
        return l_compFxConditionParams;
    }

    /**
     * (get����) <BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�Ɉ�v���� <BR>
     * ����Params��ԋp����B <BR>
     * <BR>
     * �P�jDB���� <BR>
     * ����e�[�u��(question)���ȉ��̏����Ō�������B <BR>
     * <BR>
     * <����.FX�V�X�e���R�[�h��null�ȊO�̏ꍇ><BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h = ����.���X�R�[�h<BR>
     * �@@����敪 =  ����.FX�V�X�e���R�[�h<BR>
     * <BR>
     * <����.FX�V�X�e���R�[�h��null�̏ꍇ><BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h = ����.���X�R�[�h<BR>
     * �@@����敪 =  0001 (�ב֕ۏ؋�)<BR>
     * <BR>
     * �Q�j�������ʂ��u����ԍ��v���ڂ̏����Ń\�[�g���A�ԋp����B <BR>
     * ���������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B <BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strFxSystemCode - FX�V�X�e���R�[�h
     * @@return QuestionParams[]
     * @@throws WEB3BaseException
     * @@roseuid 41C94EC1008E
     */
    public QuestionParams[] getQuestions(String l_strInstitutionCode, String l_strBranchCode,
        String l_strFxSystemCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME ="getQuestions(String l_strInstitutionCode, String l_strBranchCode, "
            + "String l_strFxSystemCode)";
        log.entering(STR_METHOD_NAME);
        
        //�P�jDB���� 
        //<����.FX�V�X�e���R�[�h��null�ȊO�̏ꍇ>
        //�@@[����]
        //�@@�،���ЃR�[�h = ����.�،���ЃR�[�h
        //�@@���X�R�[�h = ����.���X�R�[�h
        //�@@����敪 =  ����.FX�V�X�e���R�[�h
        //
        //<����.FX�V�X�e���R�[�h��null�̏ꍇ>
        //�@@[����]
        //�@@�،���ЃR�[�h = ����.�،���ЃR�[�h
        //�@@���X�R�[�h = ����.���X�R�[�h
        //�@@����敪 =  0001 (�ב֕ۏ؋�)
        String l_strWhere = "institution_code = ? and branch_code = ? and question_div = ?";

        String l_strQuestionDiv = l_strFxSystemCode;
        if (l_strFxSystemCode == null)
        {
            l_strQuestionDiv = WEB3QuestionDivDef.FX;
        }

        Object[] l_objValue = {l_strInstitutionCode, l_strBranchCode, l_strQuestionDiv};
        
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    QuestionRow.TYPE,
                    l_strWhere,
                    "to_number(question_no) asc",
                    null,
                    l_objValue);
        }
        catch (DataException l_ex)
        {
            log.debug("__DataException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j�������ʂ��u����ԍ��v���ڂ̏����Ń\�[�g���A�ԋp����B 
        //�@@���������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
        if(l_lisRows.size() == 0)
        {
            return null;
        }
        
        QuestionParams[] l_questionParams = new QuestionParams[l_lisRows.size()];
        l_lisRows.toArray(l_questionParams);

        log.exiting(STR_METHOD_NAME);
        return l_questionParams;
    }

    /**
     * (get�����) <BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�A���ʃR�[�h�Ɉ�v���� <BR>
     * �����Params��ԋp����B <BR>
     * <BR>
     * �P�jDB���� <BR>
     * ����񓚃e�[�u��(question_answer)���ȉ��̏����Ō�������B <BR>
     * <BR>
     * [����] <BR>
     * �،���ЃR�[�h = ����.�،���ЃR�[�h <BR>
     * ���X�R�[�h = ����.���X�R�[�h <BR>
     * ����敪 = ����.����敪<BR>
     * ���ʃR�[�h = ����.���ʃR�[�h <BR>
     * <BR>
     * �Q�j�������ʂ��u����ԍ��v���ڂ𐔒l�^�̏����Ń\�[�g���A�ԋp����B<BR>
     * ���������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B <BR>
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strQuestionDiv - ����敪
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@return QuestionAnswerParams[]
     * @@throws WEB3BaseException
     * @@roseuid 41BE3FF701C1
     */
    public QuestionAnswerParams[] getQuestionAnswers(
        String l_strInstitutionCode, String l_strBranchCode,
        String l_strQuestionDiv, String l_strRequestNumber) throws  WEB3BaseException
    {
        String STR_METHOD_NAME ="getQuestionAnswers(String l_strInstitutionCode, " +
            "String l_strBranchCode, String l_strQuestionDiv, String l_strRequestNumber)";
        log.entering(STR_METHOD_NAME);
        
        //�P�jDB���� 
        //�@@����񓚃e�[�u��(question_answer)���ȉ��̏����Ō�������B
        //�@@[����] 
        //�@@�،���ЃR�[�h = ����.�،���ЃR�[�h 
        //�@@���X�R�[�h = ����.���X�R�[�h 
        //  ����敪 = ����.����敪
        //�@@���ʃR�[�h = ����.���ʃR�[�h 
        String l_strWhere = "institution_code = ? and branch_code = ? " +
            "and question_div = ? and order_request_number = ?";
        
        Object[] l_objValue = {l_strInstitutionCode, l_strBranchCode, l_strQuestionDiv, l_strRequestNumber};
        
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    QuestionAnswerRow.TYPE,
                    l_strWhere,
                    "cast(question_no as integer) asc",
                    null,
                    l_objValue);
        }
        catch (DataException l_ex)
        {
            log.debug("__DataException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //�Q�j�������ʂ��u����ԍ��v���ڂ𐔒l�^�̏����Ń\�[�g���A�ԋp����B
        //�@@���������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
        if(l_lisRows.size() == 0)
        {
            return null;
        }
        
        QuestionAnswerParams[] l_questionAnswerParams = 
            new QuestionAnswerParams[l_lisRows.size()];
        l_lisRows.toArray(l_questionAnswerParams);

        log.exiting(STR_METHOD_NAME);
        return l_questionAnswerParams;
    }

    /**
     * (getGFT�����J�ݏ�) <BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�A���ʃR�[�h�� <BR>
     * �Y������GFT�����J�ݏ�Params��ԋp����B <BR>
     * <BR>
     * �P�jDB���� <BR>
     * �ȉ��̏����ŁAGFT�����J�ݏ󋵃e�[�u��(gft_account_open_status)����������B <BR>
     * <BR>
     * �،���ЃR�[�h = ����.�،���ЃR�[�h <BR>
     * ���X�R�[�h = ����.���X�R�[�h <BR>
     * ���ʃR�[�h = ����.���ʃR�[�h <BR>
     * <BR>
     * �������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B <BR>
     * <BR>
     * �Q�j�������ʂ�ԋp����B <BR>
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@return webbroker3.aio.data.GftAccountOpenStatusParams
     * @@throws WEB3BaseException
     * @@roseuid 41BE4E4C0396
     */
    public GftAccountOpenStatusParams getGFTAccountOpenStatus(
        String l_strInstitutionCode, String l_strBranchCode,
        String l_strRequestNumber) throws WEB3BaseException
    {
        String STR_METHOD_NAME ="getGFTAccountOpenStatus(String l_strInstitutionCode, " +
            "String l_strBranchCode, String l_strRequestNumber)";
        log.entering(STR_METHOD_NAME);
        
        //�P�jDB���� 
        //�@@�ȉ��̏����ŁAGFT�����J�ݏ󋵃e�[�u��(gft_account_open_status) 
        //�@@����������B 
        //�@@�،���ЃR�[�h = ����.�،���ЃR�[�h 
        //�@@���X�R�[�h = ����.���X�R�[�h 
        //�@@���ʃR�[�h = ����.���ʃR�[�h
        String l_strWhere = "institution_code = ? and branch_code = ? and order_request_number = ?";
    
        Object[] l_objValue = {l_strInstitutionCode, l_strBranchCode, l_strRequestNumber};
        
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    GftAccountOpenStatusRow.TYPE,
                    l_strWhere,
                    null,
                    l_objValue);
        }
        catch (DataException l_ex)
        {
            log.debug("__DataException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    
        //�@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
        if(l_lisRows.size() == 0)
        {
            return null;
        }
        
        if(l_lisRows.size() > 1)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "GFT�����J�ݏ󋵃e�[�u���ɕ�����");
        }
        
        //�Q�j�������ʂ�ԋp����B
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams = 
            (GftAccountOpenStatusParams)l_lisRows.get(0);
        
        log.exiting(STR_METHOD_NAME);
        return l_gftAccountOpenStatusParams;
    }

    /**
     * (getGFT�����J�ݏ�) <BR>
     * �����̏����ɊY������GFT�����J�ݏ�Params�� <BR>
     * �ꗗ��ԋp����B <BR>
     * <BR>
     * �P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B <BR>
     * <BR>
     * [doFindAllQuery()�ɃZ�b�g����p�����[�^] <BR>
     * rowType�F GFT�����J�ݏ�Row.TYPE <BR>
     * where�F �p�����[�^.�������������� <BR>
     * orderBy�F �p�����[�^.�\�[�g���� <BR>
     * conditions�F null <BR>
     * bindVars�F �p�����[�^.���������f�[�^�R���e�i <BR>
     * <BR>
     * �������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B <BR>
     * <BR>
     * �Q�j�������ʂ�ԋp����B <BR>
     * 
     * @@param l_strQueryString - ��������������
     * @@param l_queryContainer - ���������f�[�^�R���e�i
     * @@param l_strSortCond - �\�[�g����
     * @@return webbroker3.aio.data.GftAccountOpenStatusParams[]
     * @@throws WEB3BaseException
     * @@roseuid 41BD89FD0233
     */
    public GftAccountOpenStatusParams[] getGFTAccountOpenStatuses(
        String l_strQueryString, String[] l_queryContainer, String l_strSortCond)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME ="getGFTAccountOpenStatuses(String l_strQueryString," +
            " String[] l_queryContainer, String l_strSortCond)";
        log.entering(STR_METHOD_NAME);
        
        //�P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B
        //�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^] 
        //�@@�@@rowType�F�@@GFT�����J�ݏ�Row.TYPE 
        //�@@�@@where�F�@@�p�����[�^.�������������� 
        //�@@�@@orderBy�F�@@�p�����[�^.�\�[�g���� 
        //�@@�@@conditions�F�@@null 
        //�@@�@@bindVars�F�@@�p�����[�^.���������f�[�^�R���e�i 
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    GftAccountOpenStatusRow.TYPE,
                    l_strQueryString,
                    l_strSortCond,
                    null,
                    l_queryContainer);
        }
        catch (DataException l_ex)
        {
            log.debug("__DataException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }   
        
        GftAccountOpenStatusParams[] l_gftAccountOpenStatusParams = null;
        //�@@�������ʂ��擾�ł��Ȃ������ꍇ�A�v�f����0�̔z�񂪕ԋp����B
        if(l_lisRows.size() == 0)
        {
            l_gftAccountOpenStatusParams = new GftAccountOpenStatusParams[0];
            return l_gftAccountOpenStatusParams;
        }
       
        //�Q�j�������ʂ�ԋp����B
        l_gftAccountOpenStatusParams = 
            new GftAccountOpenStatusParams[l_lisRows.size()];
        
        l_lisRows.toArray(l_gftAccountOpenStatusParams);
        
        log.exiting(STR_METHOD_NAME);
        return l_gftAccountOpenStatusParams;
    }

    /**
     * (getGFT�U�֏�) <BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�A���ʃR�[�h�� <BR>
     * �Y������GFT�U�֏�Params��ԋp����B <BR>
     * <BR>
     * �P�jDB���� <BR>
     * �ȉ��̏����ŁAGFT�U�֏󋵃e�[�u��(gft_transfer_status) <BR>
     * ����������B <BR>
     * <BR>
     * �،���ЃR�[�h = ����.�،���ЃR�[�h <BR>
     * ���X�R�[�h = ����.���X�R�[�h <BR>
     * ���ʃR�[�h = ����.���ʃR�[�h <BR>
     * <BR>
     * �������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B <BR>
     * <BR>
     * �Q�j�������ʂ�ԋp����B <BR>
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@return webbroker3.aio.data.GftTransferStatusParams
     * @@throws WEB3BaseException
     * @@roseuid 41C12D9E00E9
     */
    public GftTransferStatusParams getGFTTransferStatus(
        String l_strInstitutionCode, String l_strBranchCode,
        String l_strRequestNumber) throws WEB3BaseException
    {
        String STR_METHOD_NAME ="getGFTTransferStatus(String l_strInstitutionCode," +
            " String l_strBranchCode, String l_strRequestNumber)";
        log.entering(STR_METHOD_NAME);
        
        //�P�jDB���� 
        //�@@�ȉ��̏����ŁAGFT�U�֏󋵃e�[�u��(gft_transfer_status) 
        //�@@����������B 
        //�@@�،���ЃR�[�h = ����.�،���ЃR�[�h 
        //�@@���X�R�[�h = ����.���X�R�[�h 
        //�@@���ʃR�[�h = ����.���ʃR�[�h
        String l_strWhere = "institution_code = ? and branch_code = ? and order_request_number = ?";
        
        Object[] l_objValue = {l_strInstitutionCode, l_strBranchCode, l_strRequestNumber};
        
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    GftTransferStatusRow.TYPE,
                    l_strWhere,
                    null,
                    l_objValue);
        }
        catch (DataException l_ex)
        {
            log.debug("__DataException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //�@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
        if(l_lisRows.size() == 0)
        {
            return null;
        }
        
        if(l_lisRows.size() > 1)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "GFT�U�֏󋵃e�[�u���ɕ�����");
        }
        
        //�Q�j�������ʂ�ԋp����B
        GftTransferStatusParams l_gftTransferStatusParams = 
            (GftTransferStatusParams)l_lisRows.get(0);
        
        log.exiting(STR_METHOD_NAME);
        return l_gftTransferStatusParams;
    }

    /**
     * (getGFT�U�֏�) <BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�A���o���ԍ��� <BR>
     * �Y������GFT�U�֏�Params��ԋp����B <BR>
     *  <BR>
     * �P�jDB���� <BR>
     * �@@�ȉ��̏����ŁAGFT�U�֏󋵃e�[�u��(gft_transfer_status) <BR>
     * �@@����������B <BR>
     *  <BR>
     * �@@�،���ЃR�[�h = ����.�،���ЃR�[�h <BR>
     * �@@���X�R�[�h = ����.���X�R�[�h <BR>
     * �@@���ڋq�R�[�h = ����.�ڋq�R�[�h <BR>
     * �@@���o���ԍ� = ���o���ԍ� <BR>
     *  <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B <BR>
     *  <BR>
     * ���@@�ڋq�R�[�h <BR>
     * �@@����.�ڋq�R�[�h��6���̏ꍇ�́A�ŏ���6byte�̂ݔ�r����B <BR>
     *  <BR>
     * �Q�j�������ʂ�ԋp����B <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * @@param l_strCashInOutNumber - (���o���ԍ�)<BR>
     * @@return GftTransferStatusParams
     * @@throws WEB3BaseException
     * @@roseuid 41C130DC001E
     */
    public GftTransferStatusParams getGFTTransferStatus(
        String l_strInstitutionCode, String l_strBranchCode,
        String l_strAccountCode, String l_strCashInOutNumber) throws WEB3BaseException
    {
        String STR_METHOD_NAME ="getGFTTransferStatus(String,String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�jDB����
        //�@@�ȉ��̏����ŁAGFT�U�֏󋵃e�[�u��(gft_transfer_status)
        //�@@����������B
        //�@@�،���ЃR�[�h = ����.�،���ЃR�[�h
        //�@@���X�R�[�h = ����.���X�R�[�h
        //�@@���ڋq�R�[�h = ����.�ڋq�R�[�h
        //�@@���o���ԍ� = ���o���ԍ�
        //�@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
        //���@@�ڋq�R�[�h
        //�@@����.�ڋq�R�[�h��6���̏ꍇ�́A�ŏ���6byte�̂ݔ�r����B
        String l_strWhere = " institution_code = ? " +
                            " and branch_code = ? " +
                            " and account_code = ? " +
                            " and fx_upload_number = ? ";

        l_strWhere = " institution_code = ? and branch_code = ? ";
        if (l_strAccountCode.length() == 6)
        {
            l_strWhere = l_strWhere + " and substr(account_code, 0, 6) = ? ";
        }
        else
        {
            l_strWhere = l_strWhere + " and account_code = ? ";
        }
        
        Object[] l_objValue = null ;
        if (l_strCashInOutNumber == null)
        {
            l_strWhere = l_strWhere + " and fx_upload_number is null ";
            l_objValue = new Object[3];
            l_objValue[0] = l_strInstitutionCode;
            l_objValue[1] = l_strBranchCode;
            l_objValue[2] = l_strAccountCode;
        }
        else
        {
            l_strWhere = l_strWhere + " and fx_upload_number = ? ";
            l_objValue = new Object[4];
            l_objValue[0] = l_strInstitutionCode;
            l_objValue[1] = l_strBranchCode;
            l_objValue[2] = l_strAccountCode;
            l_objValue[3] = l_strCashInOutNumber;
        }

        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    GftTransferStatusRow.TYPE,
                    l_strWhere,
                    null,
                    l_objValue);
        }
        catch (DataQueryException l_dqex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_dqex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqex.getMessage(),
                l_dqex);
        }
        catch (DataNetworkException l_dnex)
        {
            log.error("DB�A�N�Z�X�G���[", l_dnex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dnex.getMessage(),
                l_dnex);
        }
        
        //�@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
        if (l_lisRows.size() == 0)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        if (l_lisRows.size() > 1)
        {
            log.debug("GFT�U�֏󋵃e�[�u���ɕ�����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "GFT�U�֏󋵃e�[�u���ɕ�����");
        }
        
        //�Q�j�������ʂ�ԋp����B
        GftTransferStatusParams l_gftTransferStatusParams = (GftTransferStatusParams) l_lisRows.get(0);
        
        log.exiting(STR_METHOD_NAME);
        return l_gftTransferStatusParams;
    }

    /**
     * (getGFT�d���ۑ�) <BR>
     * �����̓d����ʋ敪�A�����敪�A�،���ЃR�[�h�A���X�R�[�h�A���ʃR�[�h�� <BR>
     * �Y������GFT�d���ۑ�Params��ԋp����B <BR>
     * <BR>
     * �P�jDB���� <BR>
     * �ȉ��̏����ŁAGFT�d���ۑ��e�[�u��(gft_message) <BR>
     * ����������B <BR>
     * <BR>
     * �d����ʋ敪 = ����.�d����ʋ敪 <BR>
     * �����敪 = ����.�����敪 <BR>
     * ��ЃR�[�h = ����.�،���ЃR�[�h <BR>
     * ���X�R�[�h = ����.���X�R�[�h <BR>
     * ���ʃR�[�h = ����.���ʃR�[�h <BR>
     * <BR>
     * �������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B <BR>
     * <BR>
     * �������ʂ�2���ȏ゠�����ꍇ�A��O��throw����B <BR>
     * <BR>
     * class: WEB3SystemLayerException <BR>
     * tag: SYSTEM_ERROR_80006 <BR>
     * <BR>
     * �Q�j�������ʂ�ԋp����B <BR>
     * 
     * @@param l_strMessageDiv - �d����ʋ敪
     * @@param l_strOperationDiv - �����敪
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@return webbroker3.aio.data.GftMessageParams
     * @@throws WEB3BaseException
     * @@roseuid 41C158CA01B4
     */
    public GftMessageParams getGFTMessage(String l_strMessageDiv,
        String l_strOperationDiv, String l_strInstitutionCode,
        String l_strBranchCode, String l_strRequestNumber)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME ="getGFTMessage(String l_strMessageDiv, " +
            "String l_strOperationDiv, String l_strInstitutionCode, " +
            "String l_strBranchCode, String l_strRequestNumber)";
        log.entering(STR_METHOD_NAME);

        //�P�jDB���� 
        //�@@�ȉ��̏����ŁAGFT�d���ۑ��e�[�u��(gft_message) 
        //�@@����������B
        //�@@�d����ʋ敪 = ����.�d����ʋ敪 
        //�@@�����敪 = ����.�����敪 
        //�@@��ЃR�[�h = ����.�،���ЃR�[�h 
        //�@@���X�R�[�h = ����.���X�R�[�h 
        //�@@���ʃR�[�h = ����.���ʃR�[�h 
        String l_strWhere = "message_div = ? and operation = ? " +
            "and cpy = ? and brn = ? and req = ? ";
    
        Object[] l_objValue = {l_strMessageDiv, l_strOperationDiv,
            l_strInstitutionCode, l_strBranchCode, l_strRequestNumber};
        
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    GftMessageRow.TYPE,
                    l_strWhere,
                    null,
                    l_objValue);
        }
        catch (DataException l_ex)
        {
            log.debug("__DataException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //�@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B 
        if(l_lisRows.size() == 0)
        {
            return null;
        }
        
        //�@@�������ʂ�2���ȏ゠�����ꍇ�A��O��throw����B
        if(l_lisRows.size() >= 2)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "GFT�d���ۑ��f�[�^�s�����G���[");
        }
        
        //�Q�j�������ʂ�ԋp����B
        GftMessageParams l_gftMessageParams = (GftMessageParams)l_lisRows.get(0);
        
        log.exiting(STR_METHOD_NAME);
        return l_gftMessageParams;
    }

    /**
     * (insertFX�ڋq) <BR>
     * GFT�����J�ݏ�Params�̓��e���A <BR>
     * FX�ڋq�e�[�u��(fx_account)�ɍs��insert���s���B <BR>
     * <BR>
     * �}������s�̓��e�͉��L���Q�ƁB <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �u�Ǘ��ҁE�����J�݊Ǘ�_FX�ڋq�e�[�u��DB�X�V�d�l.xls�v <BR>
     * 
     * @@param l_gftAccontOpenStatusParams - GFT�����J�ݏ�Params�I�u�W�F�N�g
     * @@param l_strUpdaterCode - �X�V�҃R�[�h
     * @@throws WEB3BaseException
     * @@roseuid 41C810BE034C
     */
    public void insertFXAccount(GftAccountOpenStatusParams l_gftAccontOpenStatusParams,
        String l_strUpdaterCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME ="insertFXAccount(GftAccountOpenStatusParams l_gftAccontOpenStatusParams, " +
            "String l_strUpdaterCode)";
        log.entering(STR_METHOD_NAME);

        if(l_gftAccontOpenStatusParams == null )
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FxAccountParams l_fxAccountParams = new FxAccountParams();
        
        //1) FX�ڋqID
        //���� : FX�f�[�^����T�[�r�XImpl.get�V�KFX�ڋqID(this.�،���ЃR�[�h, this.���X�R�[�h, 
        //this.�ڋq�R�[�h�Athis.FX�V�X�e���R�[�h)
        String l_strFXAccountID =  this.getNewFXAccountID(
                l_gftAccontOpenStatusParams.getInstitutionCode(),
                l_gftAccontOpenStatusParams.getBranchCode(), 
                l_gftAccontOpenStatusParams.getAccountCode(),
                l_gftAccontOpenStatusParams.getFxSystemCode());
            
        l_fxAccountParams.setFxAccountId(Long.parseLong(l_strFXAccountID));
        
        //2) �،���ЃR�[�h
        //���� : GFT�����J�ݏ�Params.�،���ЃR�[�h
        l_fxAccountParams.setInstitutionCode(l_gftAccontOpenStatusParams.getInstitutionCode());
        
        //3) ���X�R�[�h
        //���� : GFT�����J�ݏ�Params.���X�R�[�h
        l_fxAccountParams.setBranchCode(l_gftAccontOpenStatusParams.getBranchCode());

        //FX�V�X�e���R�[�h
        //���� : GFT�����J�ݏ�Params.FX�V�X�e���R�[�h
        l_fxAccountParams.setFxSystemCode(l_gftAccontOpenStatusParams.getFxSystemCode());

        try
        {
            //5) �ڋq�R�[�h
            //���� : GFT�����J�ݏ�Params.�ڋq�R�[�h
            l_fxAccountParams.setAccountCode(l_gftAccontOpenStatusParams.getAccountCode());
            
            //6) FX�����敪
            //���� : 1�F�J�ݍ�
            l_fxAccountParams.setFxAccountDiv(WEB3AioFxAccountOpenDivDef.OPEN);
            
            //7) ���O�i���j
            //���� : GFT�����J�ݏ�Params.���O�i���j
            l_fxAccountParams.setFxLastName(l_gftAccontOpenStatusParams.getLastName());
            
            //8) ���O�i���j
            //���� : GFT�����J�ݏ�Params.���O�i���j
            l_fxAccountParams.setFxFirstName(l_gftAccontOpenStatusParams.getFirstName());
            
            //9) FX���[���A�h���X
            //���� : GFT�����J�ݏ�Params.GFT���[���A�h���X -------> GFT�����J�ݏ�Params.���[���A�h���X
            l_fxAccountParams.setFxMailAddress(l_gftAccontOpenStatusParams.getMailAddress());
            
            //10) FX���O�C��ID
            //���� : GFT�����J�ݏ�Params.GFT���O�C��ID ---------> GFT�����J�ݏ�Params.���O�C��ID
            l_fxAccountParams.setFxLoginId(Long.parseLong(l_gftAccontOpenStatusParams.getLoginId()));
            
            //11) �X�V�҃R�[�h
            //���� : ����.�X�V�҃R�[�h
            l_fxAccountParams.setLastUpdater(l_strUpdaterCode);
            
            //12) �쐬���t
            //���� : ���ݎ���
            l_fxAccountParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //13) �X�V���t
            //���� : ���ݎ���
            l_fxAccountParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            //insert FX�ڋq�e�[�u��
            WEB3DataAccessUtility.insertRow(l_fxAccountParams);
        }
        catch (DataException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insertFX�ڋq) <BR>
     * GFT���ʒʒm�d���̓��e��FX�ڋq�e�[�u��(fx_account)�ɍs��insert���s���B <BR>
     * <BR>
     * �}������s�̓��e�͉��L���Q�ƁB <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �uFX�����J��_FX�ڋq�e�[�u��.xls�v <BR>
     * 
     * @@param l_fxGftResultNoticeTelegramUnit - GFT���ʒʒm�d������
     * @@param l_gftAccontOpenStatusParams - GFT�����J�ݏ�Params
     * @@throws WEB3BaseException
     * @@roseuid 41C97177009A
     */
    public void insertFXAccount(
            WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit, 
            GftAccountOpenStatusParams l_gftAccontOpenStatusParams)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME ="insertFXAccount(WEB3FXGftResultNoticeTelegramUnit " +
            "l_fxGftResultNoticeTelegramUnit)";
        log.entering(STR_METHOD_NAME);
        
        if(l_fxGftResultNoticeTelegramUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FxAccountParams l_fxAccountParams = new FxAccountParams();
        
        //1) FX�ڋqID
        //���� : get�V�KFX�ڋqID(this.�،���ЃR�[�h, this.���X�R�[�h, 
        //this.�ڋq�R�[�h�Athis.FX�V�X�e���R�[�h)
        String l_strFXAccountID = this.getNewFXAccountID(
            l_fxGftResultNoticeTelegramUnit.institutionCode,
            l_fxGftResultNoticeTelegramUnit.branchCode, 
            l_fxGftResultNoticeTelegramUnit.accountCode,
            l_gftAccontOpenStatusParams.getFxSystemCode());
        log.debug("l_strFXAccountID = " + l_strFXAccountID);
        l_fxAccountParams.setFxAccountId(Long.parseLong(l_strFXAccountID));
        
        //2) �،���ЃR�[�h
        //���� : ����.GFT���ʒʒm�d������.��ЃR�[�h
        l_fxAccountParams.setInstitutionCode(l_fxGftResultNoticeTelegramUnit.institutionCode);
        
        //3) ���X�R�[�h
        //���� : ����.GFT���ʒʒm�d������.���X�R�[�h
        l_fxAccountParams.setBranchCode(l_fxGftResultNoticeTelegramUnit.branchCode);

        //FX�V�X�e���R�[�h
        //���� : ����.GFT�����J�ݏ�Params.FX�V�X�e���R�[�h
        l_fxAccountParams.setFxSystemCode(l_gftAccontOpenStatusParams.getFxSystemCode());

        try
        {
            //5) �ڋq�R�[�h
            //���� : ����.GFT���ʒʒm�d������.�ڋq�R�[�h
            String l_strAccountCodeToSet = l_fxGftResultNoticeTelegramUnit.accountCode;
            if (l_strAccountCodeToSet != null && l_strAccountCodeToSet.length() == 6)
            {
                WEB3GentradeAccountManager l_web3GentradeAccountManager =
                    (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
                
                MainAccount l_mainAccount = 
                    l_web3GentradeAccountManager.getMainAccount(
                        l_fxGftResultNoticeTelegramUnit.institutionCode,
                        l_fxGftResultNoticeTelegramUnit.branchCode,
                        l_fxGftResultNoticeTelegramUnit.accountCode);
                l_strAccountCodeToSet = l_mainAccount.getAccountCode();
            }
            
            l_fxAccountParams.setAccountCode(l_strAccountCodeToSet);
            
            //6) FX�����敪
            //���� : 1�F�J�ݍ�
            l_fxAccountParams.setFxAccountDiv(WEB3AioFxAccountOpenDivDef.OPEN);
            
            //7) ���O�i���j
            //���� : ����.GFT�����J�ݏ�Params.���O�i���j
            l_fxAccountParams.setFxLastName(l_gftAccontOpenStatusParams.getLastName());
            
            //8) ���O�i���j
            //���� : ����.GFT�����J�ݏ�Params.���O�i���j
            l_fxAccountParams.setFxFirstName(l_gftAccontOpenStatusParams.getFirstName());
            
            //9) FX���[���A�h���X
            //���� : ����.GFT���ʒʒm�d������.���[���A�h���X
            l_fxAccountParams.setFxMailAddress(l_fxGftResultNoticeTelegramUnit.fxMailAddress);
            
            //10) FX���O�C��ID
            //���� : ����.GFT���ʒʒm�d������.�������O�C��ID
            l_fxAccountParams.setFxLoginId(Long.parseLong(l_fxGftResultNoticeTelegramUnit.fxFirstLoginId));
            
            //11) �X�V�҃R�[�h
            //���� : null
            l_fxAccountParams.setLastUpdater(null);
            
            //12) �쐬���t
            //���� : ���ݎ���
            l_fxAccountParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //13) �X�V���t
            //���� : ���ݎ���
            l_fxAccountParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            //insert FX�ڋq�e�[�u��
            WEB3DataAccessUtility.insertRow(l_fxAccountParams);
        }
        catch (DataException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insertFX�����ԍ�) <BR>
     * GFT�����J�ݏ�Params�AFX�������̓��e���A <BR>
     * FX�����ԍ��e�[�u��(fx_account_code)�ɍs��insert���s���B <BR>
     * <BR>
     * �}������s�̓��e�͉��L���Q�ƁB <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �u�Ǘ��ҁE�����J�݊Ǘ�_FX�����ԍ��e�[�u��DB�X�V�d�l.xls�v <BR>
     * 
     * @@param l_gftAccountOpenStatusParams - GFT�����J�ݏ�Params�I�u�W�F�N�g
     * @@param l_fxAccInformation - FX�������I�u�W�F�N�g
     * @@param l_strUpdaterCode - �X�V�҃R�[�h
     * @@throws WEB3BaseException
     * @@roseuid 41C81168021E
     */
    public void insertFXAccountCode(
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams,
        WEB3FXAccInformationUnit l_fxAccInformation, String l_strUpdaterCode)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "insertFXAccountCode(GftAccountOpenStatusParams l_gftAccountOpenStatusParams, " +
            "WEB3FXAccInformationUnit l_fxAccInformation, String l_strUpdaterCode)";
        log.entering(STR_METHOD_NAME);
        
        if(l_gftAccountOpenStatusParams == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FxAccountCodeParams l_fxAccountCodeParams = new FxAccountCodeParams();
        
        //1) �،���ЃR�[�h
        //���� : GFT�����J�ݏ�Params.�،���ЃR�[�h
        l_fxAccountCodeParams.setInstitutionCode(l_gftAccountOpenStatusParams.getInstitutionCode());
        
        //2) ���X�R�[�h
        //���� : GFT�����J�ݏ�Params.���X�R�[�h
        l_fxAccountCodeParams.setBranchCode(l_gftAccountOpenStatusParams.getBranchCode());

        //FX�V�X�e���R�[�h
        //���� : GFT�����J�ݏ�Params.FX�V�X�e���R�[�h
        l_fxAccountCodeParams.setFxSystemCode(l_gftAccountOpenStatusParams.getFxSystemCode());

        try
        {
            //4) �ڋq�R�[�h
            //���� : GFT�����J�ݏ�Params.�ڋq�R�[�h
            l_fxAccountCodeParams.setAccountCode(l_gftAccountOpenStatusParams.getAccountCode());
            
            //5) FX�R�[�X�敪
            //���� : FX�������.�R�[�X�敪
            l_fxAccountCodeParams.setFxCourseDiv(l_fxAccInformation.fxCourseDiv);
            
            //6) FX�����ԍ�
            //���� : FX�������.�����ԍ�
            l_fxAccountCodeParams.setFxAccountCode(l_fxAccInformation.fxAccountCode);

            //7) �X�V�҃R�[�h
            //���� : ����.�X�V�҃R�[�h
            l_fxAccountCodeParams.setLastUpdater(l_strUpdaterCode);
            
            //8) �쐬���t
            //���� : ���ݎ���
            l_fxAccountCodeParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //9) �X�V���t
            //���� : ���ݎ���
            l_fxAccountCodeParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            //insert FX�ڋq�e�[�u��
            WEB3DataAccessUtility.insertRow(l_fxAccountCodeParams);
        }
        catch (DataException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insertFX�����ԍ�)<BR>
     * GFT���ʒʒm�d���̓��e��FX�V�X�e���R�[�h��FX�����ԍ��e�[�u��(fx_account_code)<BR>
     * �@@�ɍs��insert���s��<BR>
     * ���ב֕ۏ؋������ԍ��������́ACFD�����ԍ���insert�����B<BR>
     * <BR>
     * insert�����͈���.GFT���ʒʒm�d������.�ב֕ۏ؋��������ꗗ�̗v�f�������{����B<BR>
     * <BR>
     * �}������s�̓��e�͉��L���Q�ƁB<BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V<BR>
     * �uFX�����J��_FX�����ԍ��e�[�u��.xls/ <BR>
     * (FX�����J��)FX�����ԍ��e�[�u��_DB�X�V�d�l�v<BR>
     * <BR>
     * @@param l_fxGftResultNoticeTelegramUnit - (GFT���ʒʒm�d������)<BR>
     * GFT���ʒʒm�d������<BR>
     * @@param l_strFxSystemCode - (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h<BR>
     * @@param l_strSimultaneousFxSystemCode - (�����J��FX�V�X�e���R�[�h)<BR>
     * �����J��FX�V�X�e���R�[�h<BR>
     * @@throws WEB3BaseException
     */
    public void insertFXAccountCode(
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit,
        String l_strFxSystemCode,
        String l_strSimultaneousFxSystemCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertFXAccountCode(WEB3FXGftResultNoticeTelegramUnit, String, String)";
        log.entering(STR_METHOD_NAME);

        if(l_fxGftResultNoticeTelegramUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //insert�����͈���.GFT���ʒʒm�d������.�ב֕ۏ؋��������ꗗ�̗v�f�������{����B
        WEB3FXAccInformationUnit[] l_fXAccInformationUnits =
            l_fxGftResultNoticeTelegramUnit.fxAccInformationList;
        int l_intFXAccInformationUnitsLength = 0;
        if (l_fXAccInformationUnits != null)
        {
        	l_intFXAccInformationUnitsLength = l_fXAccInformationUnits.length;
        }

        //�}������s�̓��e�͉��L���Q��
        //�y��Trade�z�⑫����.DB�X�V
        //�uFX�����J��_FX�����ԍ��e�[�u��.xls/
        //(FX�����J��)FX�����ԍ��e�[�u��_DB�X�V�d�l�v
        FxAccountCodeParams l_fxAccountCodeParams =
            new FxAccountCodeParams();

        //�،���ЃR�[�h: ����.GFT���ʒʒm�d������.��ЃR�[�h
        l_fxAccountCodeParams.setInstitutionCode(
            l_fxGftResultNoticeTelegramUnit.institutionCode);

        //���X�R�[�h: ����.GFT���ʒʒm�d������.���X�R�[�h
        l_fxAccountCodeParams.setBranchCode(
            l_fxGftResultNoticeTelegramUnit.branchCode);

        //�ڋq�R�[�h: ����.GFT���ʒʒm�d������.�ڋq�R�[�h
        String l_strAccountCode = l_fxGftResultNoticeTelegramUnit.accountCode;
        if (l_strAccountCode != null && l_strAccountCode.length() == 6)
        {
            WEB3GentradeAccountManager l_web3GentradeAccountManager =
                (WEB3GentradeAccountManager)GtlUtils.getAccountManager();

            MainAccount l_mainAccount =
                l_web3GentradeAccountManager.getMainAccount(
                    l_fxGftResultNoticeTelegramUnit.institutionCode,
                    l_fxGftResultNoticeTelegramUnit.branchCode,
                    l_fxGftResultNoticeTelegramUnit.accountCode);
            l_strAccountCode = l_mainAccount.getAccountCode();
        }
        l_fxAccountCodeParams.setAccountCode(l_strAccountCode);

        //�X�V�҃R�[�h: null
        l_fxAccountCodeParams.setLastUpdater(null);

        for (int i = 0; i < l_intFXAccInformationUnitsLength; i++)
        {
            //FX�V�X�e���R�[�h:
            //<����.GFT���ʒʒm�d������.�ב֕ۏ؋��������ꗗ[n].�R�[�X�敪==�h1�hor�h2�h�̏ꍇ>
            if (WEB3GftTransStatusCourseDivDef.ONE_COSE.equals(
                l_fXAccInformationUnits[i].fxCourseDiv)
                || WEB3GftTransStatusCourseDivDef.TEN_COSE.equals(
                    l_fXAccInformationUnits[i].fxCourseDiv))
            {
                //����.FX�V�X�e���R�[�h
                l_fxAccountCodeParams.setFxSystemCode(l_strFxSystemCode);
            }
            //<����.GFT���ʒʒm�d������.�ב֕ۏ؋��������ꗗ[n].�R�[�X�敪==�h3�h�̏ꍇ>
            else if (WEB3GftTransStatusCourseDivDef.CFD_COURSE.equals(
                l_fXAccInformationUnits[i].fxCourseDiv))
            {
                //<����.�����J��FX�V�X�e���R�[�h==null�̏ꍇ>
                if (WEB3StringTypeUtility.isEmpty(l_strSimultaneousFxSystemCode))
                {
                    //����.FX�V�X�e���R�[�h
                    l_fxAccountCodeParams.setFxSystemCode(l_strFxSystemCode);
                }
                //<����.�����J��FX�V�X�e���R�[�h��null�łȂ��ꍇ>
                else
                {
                    //����.�����J��FX�V�X�e���R�[�h
                    l_fxAccountCodeParams.setFxSystemCode(
                        l_strSimultaneousFxSystemCode);
                }
            }

            //FX�R�[�X�敪:����.GFT���ʒʒm����.�ב֕ۏ؋��������ꗗ[n].�R�[�X�敪
            l_fxAccountCodeParams.setFxCourseDiv(
                l_fXAccInformationUnits[i].fxCourseDiv);

            //FX�����ԍ�:����.GFT���ʒʒm����.�ב֕ۏ؋��������ꗗ[n].�����ԍ�
            l_fxAccountCodeParams.setFxAccountCode(
                l_fXAccInformationUnits[i].fxAccountCode);

            //�쐬���t: ���ݎ���
            Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
            l_fxAccountCodeParams.setCreatedTimestamp(l_tsSystemTime);

            //�X�V���t: ���ݎ���
            l_fxAccountCodeParams.setLastUpdatedTimestamp(l_tsSystemTime);

            try
            {
                WEB3DataAccessUtility.insertRow(l_fxAccountCodeParams);
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
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insert�����) <BR>
     * FX������ӎ�����̓��e�Ŏ���񓚃e�[�u��(question_answer)�ɍs��insert���s���B<BR>
     * ������.FX������ӎ�����ꗗ�̗v�f������Loop�������s���A<BR>
     * <BR>
     * �v�f���Ƃɍs��insert���s���B <BR>
     * <BR>
     * <����.FX�V�X�e���R�[�h��null�ȊO�̏ꍇ><BR>
     * �@@����敪 =  ����.FX�V�X�e���R�[�h<BR>
     * <BR>
     * <����.FX�V�X�e���R�[�h��null�̏ꍇ><BR>
     * �@@����敪 =  0001 (�ב֕ۏ؋�)<BR>
     * <BR>
     * ��L�ȊO�̍��ڂ̓��e�͉��L���Q�ƁB<BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �uFX�����J��_����񓚃e�[�u��.xls�v <BR>
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@param l_fxTradeAgreementList - FX������ӎ�����̈ꗗ
     * @@param l_strFxSystemCode - FX�V�X�e���R�[�h
     * @@throws WEB3BaseException
     * @@roseuid 41CF64BF024C
     */
    public void insertQuestionAnswer(String l_strInstitutionCode,
        String l_strBranchCode, String l_strRequestNumber,
        WEB3FXTradeAgreementUnit[] l_fxTradeAgreementList,
        String l_strFxSystemCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "insertQuestionAnswer(String l_strInstitutionCode, " +
            "String l_strBranchCode, String l_strRequestNumber, " +
            "WEB3FXTradeAgreementUnit[] l_fxTradeAgreementList, " +
            "String l_strFxSystemCode)";
        log.entering(STR_METHOD_NAME);        
        
        if(l_fxTradeAgreementList == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        {
            for(int i = 0 ; i < l_fxTradeAgreementList.length; i++)
            {
                QuestionAnswerParams l_questionAnswerParamsForUpdate = new QuestionAnswerParams();
                
                //1) �،���ЃR�[�h
                //���� : ����.�،���ЃR�[�h
                l_questionAnswerParamsForUpdate.setInstitutionCode(l_strInstitutionCode);
                
                //2) ���X�R�[�h
                //���� : ����.���X�R�[�h
                l_questionAnswerParamsForUpdate.setBranchCode(l_strBranchCode);
                
                //3) ����敪
                //����.FX�V�X�e���R�[�h��null�łȂ��ꍇ
                //�@@ ����.FX�V�X�e���R�[�h
                //����.FX�V�X�e���R�[�h��null�̏ꍇ
                //�@@�@@0001�F�ב֕ۏ؋�
                String l_strQuestionDiv = l_strFxSystemCode;
                if (l_strFxSystemCode == null)
                {
                    l_strQuestionDiv = WEB3QuestionDivDef.FX;
                }
                l_questionAnswerParamsForUpdate.setQuestionDiv(l_strQuestionDiv);
                
                //4) ���ʃR�[
                //���� : ����.���ʃR�[�h
                l_questionAnswerParamsForUpdate.setOrderRequestNumber(l_strRequestNumber);
                
                //5) ����ԍ�
                //���� : ����.FX������ӎ�����[i].����ԍ�
                l_questionAnswerParamsForUpdate.setQuestionNo(l_fxTradeAgreementList[i].questionNumber);
                
                //6) �����
                //���� : ����.FX������ӎ�����[i].�����
                l_questionAnswerParamsForUpdate.setQuestionAnswer(l_fxTradeAgreementList[i].questionAnswer);
                
                //7) �X�V�҃R�[�h
                //���� : null
                l_questionAnswerParamsForUpdate.setLastUpdatedTimestamp(null);
                
                //8) �쐬���t
                //���� : ���ݎ���
                l_questionAnswerParamsForUpdate.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                
                //9) �X�V���t
                //���� : ���ݎ���
                l_questionAnswerParamsForUpdate.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                
                //insert ����񓚃e�[�u��
                WEB3DataAccessUtility.insertRow(l_questionAnswerParamsForUpdate);
            }    
            
        }
        catch (DataException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insertGFT�����J�ݏ�) <BR>
     * GFT�˗��d���̓��e��FX�V�X�e���R�[�h<BR>
     * �@@��GFT�����J�ݏ󋵃e�[�u��(gft_account_open_status)�ɍs��insert���s���B<BR>
     * <BR>
     * �}������s�̓��e�͉��L���Q�ƁB <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �uFX�����J��_GFT�����J�ݏ󋵃e�[�u��.xls�v�� <BR>
     * �uGFT�����J�ݏ󋵃e�[�u��_DB�X�V�d�l_[�˗�]�v�V�[�g <BR>
     * @@param l_fxGftAskingTelegramUnit - GFT�˗��d������
     * @@param l_strAgreementDiv - (������敪)<BR>
     * ������敪<BR>
     * @@param l_strFxSystemCode - (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C9620F02C8
     */
    public void insertGFTAccountOpenStatus(
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit, String l_strAgreementDiv,
        String l_strFxSystemCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "insertGFTAccountOpenStatus( WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit, " +
            "String l_strAgreementDiv, String l_strFxSystemCode)";
        log.entering(STR_METHOD_NAME);
        
        if(l_fxGftAskingTelegramUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams = new GftAccountOpenStatusParams();
        try
        {
            //1) �،���ЃR�[�h
            //���� : ����.GFT�˗��d������.��ЃR�[�h
            l_gftAccountOpenStatusParams.setInstitutionCode(l_fxGftAskingTelegramUnit.institutionCode);
            
            //2) ���X�R�[�h
            //���� : ����.GFT�˗��d������.���X�R�[�h
            l_gftAccountOpenStatusParams.setBranchCode(l_fxGftAskingTelegramUnit.branchCode);
            
            //3) �ڋq�R�[�h
            //���� : ����.GFT�˗��d������.�ڋq�R�[�h
            String l_strAccountCodeToSet = l_fxGftAskingTelegramUnit.accountCode;
            if (l_strAccountCodeToSet != null && l_strAccountCodeToSet.length() == 6)
            {
                WEB3GentradeAccountManager l_web3GentradeAccountManager =
                    (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
                
                MainAccount l_mainAccount = 
                    l_web3GentradeAccountManager.getMainAccount(
                        l_fxGftAskingTelegramUnit.institutionCode,
                        l_fxGftAskingTelegramUnit.branchCode,
                        l_fxGftAskingTelegramUnit.accountCode);
                l_strAccountCodeToSet = l_mainAccount.getAccountCode();
            }
            
            l_gftAccountOpenStatusParams.setAccountCode(l_strAccountCodeToSet);
            
            //4) ���ʃR�[�h
            //���� : ����.GFT�˗��d������.���ʃR�[�h
            l_gftAccountOpenStatusParams.setOrderRequestNumber(l_fxGftAskingTelegramUnit.requestNumber);
            
            //5) ���O�i���j
            //���� : ����.GFT���ʒʒm�d������.���O�i���j
            l_gftAccountOpenStatusParams.setLastName(l_fxGftAskingTelegramUnit.fxLastName);
            
            //6) ���O�i���j
            //���� : ����.GFT�˗��d������.���O�i���j
            l_gftAccountOpenStatusParams.setFirstName(l_fxGftAskingTelegramUnit.fxFirstName);
            
            //7) ���[���A�h���X
            //���� : ����.GFT�˗��d������.���[���A�h���X
            l_gftAccountOpenStatusParams.setMailAddress(l_fxGftAskingTelegramUnit.fxMailAddress);
            
            //8) ���O�C��ID
            //���� : ����.GFT�˗��d������.�������O�C��ID
            l_gftAccountOpenStatusParams.setLoginId(l_fxGftAskingTelegramUnit.fxFirstLoginId);
    
            //9) �����p�X���[�h
            //���� : null
            l_gftAccountOpenStatusParams.setPassword(null);
            
            //10) �����ԍ��i1���ʉ݃R�[�X�j
            //���� : null
            l_gftAccountOpenStatusParams.setFxAccountCode01(null);
            
            //11) �����ԍ��i10���ʉ݃R�[�X�j
            //���� : null
            l_gftAccountOpenStatusParams.setFxAccountCode10(null);
    
            //12) �����J�ݏ󋵋敪
            //���� : 0�F�����J�ݒ�
            l_gftAccountOpenStatusParams.setAccountOpenStatusDiv(WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING);
            
            //13) ����M�敪
            //���� : 1�F���M��
            l_gftAccountOpenStatusParams.setSendRcvDiv(WEB3SendRcvDivDef.SEND_COMPLETE);
            
            //14) ��t���ʃR�[�h
            //���� : null
            l_gftAccountOpenStatusParams.setResultCode(null);
            
            //15) �G���[���R�R�[�h
            //���� : null
            l_gftAccountOpenStatusParams.setErrorReasonCode(null);
            
            //16) �X�V�҃R�[�h
            //���� : null
            l_gftAccountOpenStatusParams.setLastUpdater(null);
            
            //17) �쐬���t
            //���� : ���ݎ���
            l_gftAccountOpenStatusParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //18) �X�V���t
            //���� : ���ݎ���
            l_gftAccountOpenStatusParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            //19) ������敪
            //���� : ����.������敪
            l_gftAccountOpenStatusParams.setAgreementDiv(l_strAgreementDiv);

            //�e�w�V�X�e���R�[�h
            //����.FX�V�X�e���R�[�h
            l_gftAccountOpenStatusParams.setFxSystemCode(l_strFxSystemCode);

            //21) �A�g�p�����ԍ�
            //���� : null
            l_gftAccountOpenStatusParams.setExtAccountCode(null);

            //insert GFT�����J�ݏ󋵃e�[�u��
            WEB3DataAccessUtility.insertRow(l_gftAccountOpenStatusParams);
        }
        catch (DataException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insertGFT�U�֏�) <BR>
     * GFT�˗��d���̓��e��GFT�U�֏󋵃e�[�u��(gft_transfer_status)�ɍs��insert���s���B<BR>
     * �}������s�̓��e�͉��L���Q�ƁB <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �uFX�U�֋���_GFT�U�֏󋵃e�[�u��.xls�v�� <BR>
     * �uGFT�U�֏󋵃e�[�u��_DB�X�V�d�l_[�˗�]�v�V�[�g <BR>
     * 
     * @@param l_fxGftAskingTelegramUnit - GFT�˗��d������
     * @@param l_strCourseDiv - �R�[�X�敪
     * @@param l_strTransferDate - ��n�\���
     * @@param l_strMrgTrnRequestNumber - �M�p�U�֗p���ʃR�[�h
     * ���M�p��������̋����U�ւ��s��Ȃ��ꍇ�Anull
     * @@param l_compFxConditionParams - ��Е�FX�V�X�e������Params
     * @@param l_strIoListTradeDiv - ���o���ꗗ����敪
     * @@throws WEB3BaseException
     * @@roseuid 41BE98D10109
     */
    public void insertGFTTransferStatus(
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit,
        String l_strCourseDiv, String l_strTransferDate,
        String l_strMrgTrnRequestNumber,
        CompFxConditionParams l_compFxConditionParams,
        String l_strIoListTradeDiv) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "insertGFTTransferStatus(WEB3FXGftAskingTelegramUnit, " +
            "String, String, String, CompFxConditionParams, String)";
        log.entering(STR_METHOD_NAME);
        
        if(l_fxGftAskingTelegramUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        GftTransferStatusParams l_gftTransferStatusParams = new GftTransferStatusParams();
        
        try
        {
            //1) �،���ЃR�[�h
            //���� : ����.GFT�˗��d������.��ЃR�[�h
            l_gftTransferStatusParams.setInstitutionCode(l_fxGftAskingTelegramUnit.institutionCode);
    
            //2) ���X�R�[�h
            //���� : ����.GFT�˗��d������.���X�R�[�h
            l_gftTransferStatusParams.setBranchCode(l_fxGftAskingTelegramUnit.branchCode);
    
            //3) �ڋq�R�[�h
            //���� : ����.GFT�˗��d������.�ڋq�R�[�h
            String l_strAccountCodeToSet = l_fxGftAskingTelegramUnit.accountCode;
            if (l_strAccountCodeToSet != null && l_strAccountCodeToSet.length() == 6)
            {
                WEB3GentradeAccountManager l_web3GentradeAccountManager =
                    (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
                
                MainAccount l_mainAccount = 
                    l_web3GentradeAccountManager.getMainAccount(
                        l_fxGftAskingTelegramUnit.institutionCode,
                        l_fxGftAskingTelegramUnit.branchCode,
                        l_fxGftAskingTelegramUnit.accountCode);
                l_strAccountCodeToSet = l_mainAccount.getAccountCode();
            }
            
            l_gftTransferStatusParams.setAccountCode(l_strAccountCodeToSet);
            
            //4) ���ʃR�[�h
            //���� : ����.GFT�˗��d������.���ʃR�[�h
            l_gftTransferStatusParams.setOrderRequestNumber(l_fxGftAskingTelegramUnit.requestNumber);
    
            //5) �����敪
            //���� : 
            //����.GFT�˗��d������.�����敪��04�F�o��(FX)�̏ꍇ�A01�i�،���������ב֕ۏ؋��ցj
            if(WEB3GftMessageOperationDef.CASH_OUT_FX.equals(l_fxGftAskingTelegramUnit.gftOperationDiv))
            {
                l_gftTransferStatusParams.setOperationDiv(WEB3FxTransStatusOperationDivDef.TO_FX);
            }
            //����.GFT�˗��d������.�����敪��02�F����(FX)�̏ꍇ�A02�i�ב֕ۏ؋�����،������ցj
            else if(WEB3GftMessageOperationDef.CASH_IN_FX.equals(l_fxGftAskingTelegramUnit.gftOperationDiv))
            {
                l_gftTransferStatusParams.setOperationDiv(WEB3FxTransStatusOperationDivDef.FROM_FX);
            }
            //����.GFT�˗��d������.�����敪��06�F�o��(��OP)�̏ꍇ�A03�i�،��������犔��؋����ցj 
            else if(WEB3GftMessageOperationDef.CASH_OUT_FUOP.equals(l_fxGftAskingTelegramUnit.gftOperationDiv))
            {
                l_gftTransferStatusParams.setOperationDiv(WEB3FxTransStatusOperationDivDef.TO_FUOP);
            }
            //����.GFT�˗��d������.�����敪��05�F����(��OP)�̏ꍇ�A04�i����؋�������،������ցj 
            else if(WEB3GftMessageOperationDef.CASH_IN_FUOP.equals(l_fxGftAskingTelegramUnit.gftOperationDiv))
            {
                l_gftTransferStatusParams.setOperationDiv(WEB3FxTransStatusOperationDivDef.FROM_FUOP);
            }
            
            //6) �R�[�X�敪
            //���� : ����.GFT�R�[�X�敪
            l_gftTransferStatusParams.setCourseDiv(l_strCourseDiv);                
            
            //7) �����ԍ�
            //���� : ����.GFT�˗��d������.�ב֕ۏ؋������ԍ�
            l_gftTransferStatusParams.setFxAccountCode(l_fxGftAskingTelegramUnit.fxAccountCode);
            
            //8) ���z
            //���� : ����.GFT�˗��d������.���o���z
            l_gftTransferStatusParams.setAmount(Long.parseLong(l_fxGftAskingTelegramUnit.cashinoutAmt));
            
            //9) ��n�\���
            //���� : ����.��n�\���
            l_gftTransferStatusParams.setTransferDate(l_strTransferDate);  
            
            //10) �U�֏󋵋敪
            //���� : 0�F���ϒ�
            l_gftTransferStatusParams.setTransferStatusDiv(WEB3TransferStatusDivDef.PROCESSING);   
            
            //11) ����M�敪
            //���� : 1�F���M��
            l_gftTransferStatusParams.setSendRcvDiv(WEB3SendRcvDivDef.SEND_COMPLETE);
    
            //12) ��t���ʃR�[�h
            //���� : null
            l_gftTransferStatusParams.setResultCode(null);
    
            //13) �G���[���R�R�[�h
            //���� : null
            l_gftTransferStatusParams.setErrorReasonCode(null);
            
            //14) �������ԁi���M�j
            //���� : ����.GFT�˗��d������.DIR��GFT���M����
            l_gftTransferStatusParams.setSendTime(l_fxGftAskingTelegramUnit.dirSendTime);
    
            //15) �������ԁi��M�j
            //���� : null
            l_gftTransferStatusParams.setReceiveTime(null);
            
            //16) �M�p�U�֗p���ʃR�[�h
            //���� : ����.�M�p�U�֗p���ʃR�[�h
            l_gftTransferStatusParams.setMrgTrnOrderRequestNumber(l_strMrgTrnRequestNumber);
            
            //17) �X�V�҃R�[�h
            //���� : null
            l_gftTransferStatusParams.setLastUpdater(null);
            
            //18) �쐬���t
            //���� : ���ݎ���
            l_gftTransferStatusParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //19) �X�V���t
            //���� : ���ݎ���
            l_gftTransferStatusParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            //�e�w�V�X�e���R�[�h
            //����.��Е�FX�V�X�e������Params.FX�V�X�e���R�[�h
            l_gftTransferStatusParams.setFxSystemCode(
                l_compFxConditionParams.getFxSystemCode());

            //���o���ꗗ����敪
            //���� : ����.���o���ꗗ����敪
            l_gftTransferStatusParams.setIoListTradeDiv(l_strIoListTradeDiv);

            //insert GFT�U�֏󋵃e�[�u��
            WEB3DataAccessUtility.insertRow(l_gftTransferStatusParams);
        
        }
        catch (DataException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insertGFT�U�֏�) <BR>
     * GFT�˗��d���̓��e��GFT�U�֏󋵃e�[�u��(gft_transfer_status)�ɍs��insert���s���B <BR>
     *  <BR>
     * �}������s�̓��e�͉��L���Q�ƁB <BR>
     *  <BR>
     * �y��Trade�z�⑫����.DB�X�V  <BR>
     * �uFX�U�֋���_GFT�U�֏󋵃e�[�u��.xls�v <BR>
     * @@param l_fxGftAskingTelegramUnit - (GFT�˗��d������)<BR>
     * @@param l_strCourseDiv - (�R�[�X�敪)<BR>
     * @@param l_strTransferDate - (��n�\���)<BR>
     * @@param l_strMrgTrnRequestNumber - (�M�p�U�֗p���ʃR�[�h)<BR>
     * @@param l_strCashInOutNumber - (���o���ԍ�)<BR>
     * @@param l_strIoListTradeDiv - ���o���ꗗ����敪<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41BE98D10109
     */
    public void insertGFTTransferStatus(
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit,
        String l_strCourseDiv,
        String l_strTransferDate,
        String l_strMrgTrnRequestNumber,
        String l_strCashInOutNumber,
        String l_strIoListTradeDiv) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "insertGFTTransferStatus(WEB3FXGftAskingTelegramUnit, " +
            "String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_fxGftAskingTelegramUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        GftTransferStatusParams l_gftTransferStatusParams = new GftTransferStatusParams();
        
        try
        {
            //1) �،���ЃR�[�h
            //���� : ����.GFT�˗��d������.��ЃR�[�h
            l_gftTransferStatusParams.setInstitutionCode(l_fxGftAskingTelegramUnit.institutionCode);
    
            //2) ���X�R�[�h
            //���� : ����.GFT�˗��d������.���X�R�[�h
            l_gftTransferStatusParams.setBranchCode(l_fxGftAskingTelegramUnit.branchCode);
    
            //3) �ڋq�R�[�h
            //���� : ����.GFT�˗��d������.�ڋq�R�[�h
            String l_strAccountCodeToSet = l_fxGftAskingTelegramUnit.accountCode;
            if (l_strAccountCodeToSet != null && l_strAccountCodeToSet.length() == 6)
            {
                WEB3GentradeAccountManager l_web3GentradeAccountManager =
                    (WEB3GentradeAccountManager) GtlUtils.getAccountManager();
                
                MainAccount l_mainAccount = 
                    l_web3GentradeAccountManager.getMainAccount(
                        l_fxGftAskingTelegramUnit.institutionCode,
                        l_fxGftAskingTelegramUnit.branchCode,
                        l_fxGftAskingTelegramUnit.accountCode);
                l_strAccountCodeToSet = l_mainAccount.getAccountCode();
            }
            
            l_gftTransferStatusParams.setAccountCode(l_strAccountCodeToSet);
            
            //4) ���ʃR�[�h
            //���� : ����.GFT�˗��d������.���ʃR�[�h
            l_gftTransferStatusParams.setOrderRequestNumber(l_fxGftAskingTelegramUnit.requestNumber);
    
            //5) �����敪
            //���� : 
            //02�i�ב֕ۏ؋�����،������ցj�@@
            l_gftTransferStatusParams.setOperationDiv(WEB3FxTransStatusOperationDivDef.FROM_FX);
            
            //6) �R�[�X�敪
            //���� : ����.GFT�R�[�X�敪
            l_gftTransferStatusParams.setCourseDiv(l_strCourseDiv);                
            
            //7) �����ԍ�
            //���� : ����.GFT�˗��d������.�ב֕ۏ؋������ԍ�
            l_gftTransferStatusParams.setFxAccountCode(l_fxGftAskingTelegramUnit.fxAccountCode);
            
            //8) ���z
            //���� : ����.GFT�˗��d������.���o���z
            l_gftTransferStatusParams.setAmount(Long.parseLong(l_fxGftAskingTelegramUnit.cashinoutAmt));
            
            //9) ��n�\���
            //���� : ����.��n�\���
            l_gftTransferStatusParams.setTransferDate(l_strTransferDate);  
            
            //10) �U�֏󋵋敪
            //���� : 1�F���ϊ���
            l_gftTransferStatusParams.setTransferStatusDiv(WEB3TransferStatusDivDef.PROCESS_COMPLETE);   
            
            //11) ����M�敪
            //���� : 2�F��M��
            l_gftTransferStatusParams.setSendRcvDiv(WEB3SendRcvDivDef.RECEIVE_COMPLETE);
    
            //12) ��t���ʃR�[�h
            //���� : 00000000�F����
            l_gftTransferStatusParams.setResultCode(WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000);
    
            //13) �G���[���R�R�[�h
            //���� : 0000�F����
            l_gftTransferStatusParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            
            //14) �������ԁi���M�j
            //���� : ����.GFT�˗��d������.DIR��GFT���M����
            l_gftTransferStatusParams.setSendTime(l_fxGftAskingTelegramUnit.dirSendTime);
    
            //15) �������ԁi��M�j
            //���� : null
            l_gftTransferStatusParams.setReceiveTime(null);
            
            //16) �M�p�U�֗p���ʃR�[�h
            //���� : ����.�M�p�U�֗p���ʃR�[�h
            l_gftTransferStatusParams.setMrgTrnOrderRequestNumber(l_strMrgTrnRequestNumber);
            
            //17) �X�V�҃R�[�h
            //���� : null
            l_gftTransferStatusParams.setLastUpdater(null);
            
            //18) �쐬���t
            //���� : ���ݎ���
            l_gftTransferStatusParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //19) �X�V���t
            //���� : ���ݎ���
            l_gftTransferStatusParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //20) ���o���ԍ�
            //���� : ����.���o���ԍ�
            l_gftTransferStatusParams.setFxUploadNumber(l_strCashInOutNumber);

            //21) ��t���ʃR�[�h�iSOAP�j
            //���� : null
            l_gftTransferStatusParams.setResultCodeSoap(null);

            //�e�w�V�X�e���R�[�h
            //���� : null
            l_gftTransferStatusParams.setFxSystemCode(null);

            //���o���ꗗ����敪
            //���� : ����.���o���ꗗ����敪
            l_gftTransferStatusParams.setIoListTradeDiv(l_strIoListTradeDiv);

            //insert GFT�U�֏󋵃e�[�u��
            WEB3DataAccessUtility.insertRow(l_gftTransferStatusParams);
        }
        catch (DataQueryException l_dqex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_dqex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqex.getMessage(),
                l_dqex);
        }
        catch (DataNetworkException l_dnex)
        {
            log.error("DB�A�N�Z�X�G���[", l_dnex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dnex.getMessage(),
                l_dnex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insertGFT�d���ۑ�) <BR>
     * GFT�˗��d���̓��e��GFT�d���ۑ��e�[�u��(gft_transfer_status)�ɍs��insert���s���B<BR>
     * �}������s�̓��e�͉��L���Q�ƁB <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �uFX����_GFT�d���ۑ��e�[�u��.xls�v�� <BR>
     * �uGFT�d���ۑ��e�[�u��_DB�X�V�d�l[�˗�]�v�V�[�g <BR>
     * @@param l_fxGftAskingTelegramUnit - GFT�˗��d������
     * @@throws WEB3BaseException
     * @@roseuid 41BEA44A006D
     */
    public void insertGFTMessage(WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "insertGFTMessage(WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit) ";
        log.entering(STR_METHOD_NAME);
        
        if(l_fxGftAskingTelegramUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        GftMessageParams l_gftMessageParams = new GftMessageParams();
        
        try
        {
            //1) �d����ʋ敪
            //���� : 1�F���M�iDIR��GFT�j
            l_gftMessageParams.setMessageDiv(WEB3GftMessageDivDef.SEND);
            
            //2) DIR��GFT���M����
            //���� : ����.GFT�˗��d������.DIR��GFT���M����
            l_gftMessageParams.setDirSendTime(l_fxGftAskingTelegramUnit.dirSendTime);
            
            //3) �����敪
            //���� : ����.GFT�˗��d������.�����敪
            l_gftMessageParams.setOperation(l_fxGftAskingTelegramUnit.gftOperationDiv);        
            
            //4) �ב֕ۏ؋������ԍ�
            //���� : ����.GFT�˗��d������.�ב֕ۏ؋������ԍ�
            l_gftMessageParams.setAccount(l_fxGftAskingTelegramUnit.fxAccountCode);        
    
            //5) ���[���A�h���X
            //���� : ����.GFT�˗��d������.���[���A�h���X
            l_gftMessageParams.setEmail(l_fxGftAskingTelegramUnit.fxMailAddress);
            
            //6) �������O�C��ID
            //���� : ����.GFT�˗��d������.�������O�C��ID
            l_gftMessageParams.setGftLink1(l_fxGftAskingTelegramUnit.fxFirstLoginId);        
            
            //7) �����p�X���[�h
            //���� : ����.GFT�˗��d������.�����p�X���[�h
            WEB3FXTelegramProcessService l_fXTelegramProcessService = 
                (WEB3FXTelegramProcessService)Services.getService(WEB3FXTelegramProcessService.class);
            
            l_gftMessageParams.setGftLink2(
                l_fXTelegramProcessService.maskPassword(l_fxGftAskingTelegramUnit.fxFirstPassword));        
            
            //8) �S���敪
            //���� : ����.GFT�˗��d������.�S���敪
            l_gftMessageParams.setGroupName(l_fxGftAskingTelegramUnit.groupName);
            
            //9) ���o���z
            //���� : ����.GFT�˗��d������.���o���z
            l_gftMessageParams.setAmount(l_fxGftAskingTelegramUnit.cashinoutAmt);        
            
            //10) WOLF�Z�b�V�����L�[
            //���� : ����.GFT�˗��d������.WOLF�Z�b�V�����L�[
            l_gftMessageParams.setWolfSessionKey(l_fxGftAskingTelegramUnit.wolfSession);    
            
            //11) �A�v���P�[�V����ID
            //���� : ����.GFT�˗��d������.�A�v���P�[�V����ID
            l_gftMessageParams.setAaAid(l_fxGftAskingTelegramUnit.wolfAid);  
            
            //12) �Đ����T�[�r�XID
            //���� : ����.GFT�˗��d������.�Đ����T�[�r�XID
            l_gftMessageParams.setAaRsid(l_fxGftAskingTelegramUnit.regetServiceId); 
            
            //13) SSID
            //���� : ����.GFT�˗��d������.SSID
            l_gftMessageParams.setSsid(l_fxGftAskingTelegramUnit.wolfSsid); 
            
            //14) ��ЃR�[�h
            //���� : ����.GFT�˗��d������.��ЃR�[�h
            l_gftMessageParams.setCpy(l_fxGftAskingTelegramUnit.institutionCode);        
            
            //15) ���X�R�[�h
            //���� : ����.GFT�˗��d������.���X�R�[�h
            l_gftMessageParams.setBrn(l_fxGftAskingTelegramUnit.branchCode);  
            
            //16) �ڋq�R�[�h
            //���� : ����.GFT�˗��d������.�ڋq�R�[�h
            String l_strAccountCodeToSet = l_fxGftAskingTelegramUnit.accountCode;
            if (l_strAccountCodeToSet != null && l_strAccountCodeToSet.length() == 6)
            {
                WEB3GentradeAccountManager l_web3GentradeAccountManager =
                    (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
                
                MainAccount l_mainAccount = 
                    l_web3GentradeAccountManager.getMainAccount(
                        l_fxGftAskingTelegramUnit.institutionCode,
                        l_fxGftAskingTelegramUnit.branchCode,
                        l_fxGftAskingTelegramUnit.accountCode);
                l_strAccountCodeToSet = l_mainAccount.getAccountCode();
            }
            
            l_gftMessageParams.setAcc(l_strAccountCodeToSet);        
    
            //17) ���ʃR�[�h
            //���� : ����.GFT�˗��d������.���ʃR�[�h
            l_gftMessageParams.setReq(l_fxGftAskingTelegramUnit.requestNumber);
    
            //18) ��t����
            //���� : null
            l_gftMessageParams.setResultCode(null);
    
            //19) GFT��DIR���M����
            //���� : null
            l_gftMessageParams.setGftSendTime(null);
            
            //20) �ב֕ۏ؋������ԍ��i�P���ʉ݁j
            //���� : null
            l_gftMessageParams.setGftAc1(null);
            
            //21) �ב֕ۏ؋������ԍ��i�P�O���ʉ݁j
            //���� : null
            l_gftMessageParams.setGftAc2(null);
            
            //22) ���O�i���j
            //���� : ����.GFT�˗��d������.���O�i���j
            l_gftMessageParams.setLastName(l_fxGftAskingTelegramUnit.fxLastName);
            
            //23) ���O�i���j
            //���� : ����.GFT�˗��d������.���O�i���j
            l_gftMessageParams.setFirstName(l_fxGftAskingTelegramUnit.fxFirstName);        
            
            //24) �n�b�V���l
            //���� : ����.GFT�˗��d������.�n�b�V���l
            l_gftMessageParams.setHashKey(l_fxGftAskingTelegramUnit.hashValue);        
            
            //25) �쐬���t
            //���� : ���ݎ���
            l_gftMessageParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //26) �X�V���t
            //���� : ���ݎ���
            l_gftMessageParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
           
            //27) ��n��
            //���� : ��n��
            l_gftMessageParams.setDeliveryDate(l_fxGftAskingTelegramUnit.deliveryDate);
            
            //28) ���o���z2
            //���� : null
            l_gftMessageParams.setAmount2(null);

            //�Z���P
            //���� :����.GFT�˗��d������.�Z���P
            l_gftMessageParams.setAddress1(l_fxGftAskingTelegramUnit.address1);

            //�Z���Q
            //���� :����.GFT�˗��d������.�Z���Q
            l_gftMessageParams.setAddress2(l_fxGftAskingTelegramUnit.address2);

            //�Z���R
            //���� :����.GFT�˗��d������.�Z���R
            l_gftMessageParams.setAddress3(l_fxGftAskingTelegramUnit.address3);

            //insert FX����_GFT�d���ۑ��e�[�u��
            WEB3DataAccessUtility.insertRow(l_gftMessageParams);
    
        }
        catch (DataException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insertGFT�d���ۑ�) <BR>
     * GFT���ʒʒm�d���̓��e��GFT�d���ۑ��e�[�u��(gft_transfer_status) <BR>
     * �ɍs��insert���s�� �B<BR>
     * <BR>
     * �}������s�̓��e�͉��L���Q�ƁB <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �uFX����_GFT�d���ۑ��e�[�u��.xls�v�� <BR>
     * �uGFT�d���ۑ��e�[�u��_DB�X�V�d�l[���ʒʒm]�v�V�[�g <BR>
     * @@param l_fxGftResultNoticeTelegramUnit - GFT���ʒʒm�d������
     * @@throws WEB3BaseException
     * @@roseuid 41C10C040369
     */
    public void insertGFTMessage(
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "insertGFTMessage(WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit)";
        log.entering(STR_METHOD_NAME);
        
        if(l_fxGftResultNoticeTelegramUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        GftMessageParams l_gftMessageParams = new GftMessageParams();
        
        try
        {
            //1) �d����ʋ敪
            //���� : 2�F��M�iGFT��DIR)
            l_gftMessageParams.setMessageDiv(WEB3GftMessageDivDef.RECEIVE);
            
            //2) DIR��GFT���M����
            //���� : ����.GFT���ʒʒm�d������.DIR��GFT���M����
            l_gftMessageParams.setDirSendTime(l_fxGftResultNoticeTelegramUnit.dirSendTime);
            
            //3) �����敪
            //���� : ����.GFT���ʒʒm�d������.�����敪
            l_gftMessageParams.setOperation(l_fxGftResultNoticeTelegramUnit.gftOperationDiv);        
            
            //4) �ב֕ۏ؋������ԍ�
            //���� : ����.GFT���ʒʒm�d������.�ב֕ۏ؋��ԍ�---------->�ב֕ۏ؋������ԍ�
            l_gftMessageParams.setAccount(l_fxGftResultNoticeTelegramUnit.fxAccountCode);        
    
            //5) ���[���A�h���X
            //���� : ����.GFT���ʒʒm�d������.���[���A�h���X
            l_gftMessageParams.setEmail(l_fxGftResultNoticeTelegramUnit.fxMailAddress);
            
            //6) �������O�C��ID
            //���� : ����.GFT���ʒʒm�d������.�������O�C��ID
            l_gftMessageParams.setGftLink1(l_fxGftResultNoticeTelegramUnit.fxFirstLoginId);        
            
            //7) �����p�X���[�h
            //���� : ����.GFT���ʒʒm�d������.�����p�X���[�h
            WEB3FXTelegramProcessService l_fXTelegramProcessService = 
                (WEB3FXTelegramProcessService)Services.getService(WEB3FXTelegramProcessService.class);
            
            l_gftMessageParams.setGftLink2(
                l_fXTelegramProcessService.maskPassword(l_fxGftResultNoticeTelegramUnit.fxFirstPassword));        
            
            //8) �S���敪
            //���� : ����.GFT���ʒʒm�d������.�S���敪
            l_gftMessageParams.setGroupName(l_fxGftResultNoticeTelegramUnit.groupName);
            
            //9) ���o���z
            //���� : ����.GFT���ʒʒm�d������.���o���z
            l_gftMessageParams.setAmount(l_fxGftResultNoticeTelegramUnit.cashinoutAmt);        
            
            //10) WOLF�Z�b�V�����L�[
            //���� : ����.GFT���ʒʒm�d������.WOLF�Z�b�V�����L�[
            l_gftMessageParams.setWolfSessionKey(l_fxGftResultNoticeTelegramUnit.wolfSession);    
            
            //11) �A�v���P�[�V����ID
            //���� : ����.GFT���ʒʒm�d������.�A�v���P�[�V����ID
            l_gftMessageParams.setAaAid(l_fxGftResultNoticeTelegramUnit.wolfAid);  
            
            //12) �Đ����T�[�r�XID
            //���� : ����.GFT���ʒʒm�d������.�Đ����T�[�r�XID
            l_gftMessageParams.setAaRsid(l_fxGftResultNoticeTelegramUnit.regetServiceId); 
            
            //13) SSID
            //���� : ����.GFT���ʒʒm�d������.SSID
            l_gftMessageParams.setSsid(l_fxGftResultNoticeTelegramUnit.wolfSsid); 
            
            //14) ��ЃR�[�h
            //���� : ����.GFT���ʒʒm�d������.��ЃR�[�h
            l_gftMessageParams.setCpy(l_fxGftResultNoticeTelegramUnit.institutionCode);        
            
            //15) ���X�R�[�h
            //���� : ����.GFT���ʒʒm�d������.���X�R�[�h
            l_gftMessageParams.setBrn(l_fxGftResultNoticeTelegramUnit.branchCode);  
            
            //16) �ڋq�R�[�h
            //���� : ����.GFT���ʒʒm�d������.�ڋq�R�[�h
            String l_strAccountCodeToSet = l_fxGftResultNoticeTelegramUnit.accountCode;
            if (l_strAccountCodeToSet != null && l_strAccountCodeToSet.length() == 6)
            {
                WEB3GentradeAccountManager l_web3GentradeAccountManager =
                    (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
                
                MainAccount l_mainAccount = 
                    l_web3GentradeAccountManager.getMainAccount(
                        l_fxGftResultNoticeTelegramUnit.institutionCode,
                        l_fxGftResultNoticeTelegramUnit.branchCode,
                        l_fxGftResultNoticeTelegramUnit.accountCode);
                l_strAccountCodeToSet = l_mainAccount.getAccountCode();
            }
            
            l_gftMessageParams.setAcc(l_strAccountCodeToSet);        

            //17) ���ʃR�[�h
            //���� : ����.GFT���ʒʒm�d������.���ʃR�[�h
            l_gftMessageParams.setReq(l_fxGftResultNoticeTelegramUnit.requestNumber);
    
            //18) ��t����
            //���� : ����.GFT���ʒʒm�d������.��t����
            l_gftMessageParams.setResultCode(l_fxGftResultNoticeTelegramUnit.resultCode);
    
            //19) GFT��DIR���M����
            //���� : ����.GFT���ʒʒm�d������.GFT��DIR���M����
            l_gftMessageParams.setGftSendTime(l_fxGftResultNoticeTelegramUnit.gftSendTime);
            
            //20) �ב֕ۏ؋������ԍ��i�P���ʉ݁j
            //���� : ����.GFT���ʒʒm�d������.�ב֕ۏ؋������ԍ�(1���ʉ�)
            l_gftMessageParams.setGftAc1(l_fxGftResultNoticeTelegramUnit.gftAcc1);
            
            //21) �ב֕ۏ؋������ԍ��i�P�O���ʉ݁j
            //���� : ����.GFT���ʒʒm�d������.�ב֕ۏ؋������ԍ�(10���ʉ�)
            l_gftMessageParams.setGftAc2(l_fxGftResultNoticeTelegramUnit.gftAcc2);
            
            //22) ���O�i���j
            //���� : ����.GFT���ʒʒm�d������.���O�i���j
            l_gftMessageParams.setLastName(l_fxGftResultNoticeTelegramUnit.fxLastName);
            
            //23) ���O�i���j
            //���� : ����.GFT���ʒʒm�d������.���O�i���j
            l_gftMessageParams.setFirstName(l_fxGftResultNoticeTelegramUnit.fxFirstName);        
            
            //24) �n�b�V���l
            //���� : ����.GFT���ʒʒm�d������.�n�b�V���l
            l_gftMessageParams.setHashKey(l_fxGftResultNoticeTelegramUnit.hashValue);        
            
            //25) �쐬���t
            //���� : ���ݎ���
            l_gftMessageParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //26) �X�V���t
            //���� : ���ݎ���
            l_gftMessageParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
           
            //27)��n��
            //���� :��n��
            l_gftMessageParams.setDeliveryDate(l_fxGftResultNoticeTelegramUnit.deliveryDate);

            //28) ���o���z2
            //���� : ����.GFT���ʒʒm�d������.���o���z2
            l_gftMessageParams.setAmount2(l_fxGftResultNoticeTelegramUnit.cashinoutAmt2);

            //�Z���P
            //���� : null
            l_gftMessageParams.setAddress1(null);

            //�Z���Q
            //���� : null
            l_gftMessageParams.setAddress2(null);

            //�Z���R
            //���� : null
            l_gftMessageParams.setAddress3(null);

            //insert FX����_GFT�d���ۑ��e�[�u��
            WEB3DataAccessUtility.insertRow(l_gftMessageParams);
    
        }
        catch (DataException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (updateFX�ڋq) <BR>
     * �X�V��̒l��FX�ڋq�e�[�u���̌����J�ݏ󋵋敪���X�V����B <BR>
     * <BR>
     * (1)FX�ڋqParams�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * (2)���������C���X�^���X�ɁA����.FX�ڋqParam�̃v���p�e�B���R�s�[����B<BR>
     * <BR>
     * (3)���������C���X�^���X�ɍX�V��̃f�[�^���Z�b�g����B<BR>
     * �X�V����s�̓��e�͉��L���Q�ƁB<BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �u�Ǘ��ҁE�����Ǘ�_FX�ڋq�e�[�u��DB�X�V�d�l.xls�v<BR>
     * <BR>
     * (4)FX�ڋq��update<BR>
     * �@@�@@ WEB3DataAccessUtility.updateRow()���\�b�h���R�[������B<BR>
     * �@@�@@�@@[updateRow()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@l_row�F�@@�X�V��̃f�[�^���Z�b�g�����C���X�^���X<BR>
     * <BR>
     * @@param l_fxAccountParams - FX�ڋqParam�I�u�W�F�N�g
     * @@param l_strUpdatedAccOpenStatusDiv - �X�V������J�ݏ󋵋敪
     * @@param l_strUpdaterCode - �X�V�҃R�[�h
     * @@throws WEB3BaseException
     * @@roseuid 41BEEAAF01FD
     */
    public void updateFXAccount(FxAccountParams l_fxAccountParams,
        String l_strUpdatedAccOpenStatusDiv,
        String l_strUpdaterCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateFXAccount(FxAccountParams l_fxAccountParams," +
            "String l_strUpdatedAccOpenStatusDiv, " +
            "String l_strUpdaterCode)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_fxAccountParams == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // (1)FX�ڋqParams�C���X�^���X�𐶐�����B
        // (2)���������C���X�^���X�ɁA����.FX�ڋqParam�̃v���p�e�B���R�s�[����B
        HashMap l_hmUpdateValues = new HashMap();
        try
        {
            
            // (3)���������C���X�^���X�ɍX�V��̃f�[�^���Z�b�g����B
            //�@@�X�V����s�̓��e�͉��L���Q�ƁB 
            //�@@�y��Trade�z�⑫����.DB�X�V 
            //�@@�u�Ǘ��ҁE�����Ǘ�_FX�ڋq�e�[�u��DB�X�V�d�l.xls�v
            
            //3.1) FX�����敪
            //���� : ����.�X�V������J�ݏ󋵋敪
            l_hmUpdateValues.put("fx_account_div", l_strUpdatedAccOpenStatusDiv);
            
            //3.2) �X�V�҃R�[�h
            //���� : ����.�X�V�҃R�[�h
            l_hmUpdateValues.put("last_updater", l_strUpdaterCode);
            
            //3.3) �X�V���t
            //���� : ���ݎ���
            l_hmUpdateValues.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
            
            // (4)FX�ڋq��update
            //WEB3DataAccessUtility.updateRow()���\�b�h���R�[������B 
            //�@@[updateRow()�ɃZ�b�g����p�����[�^] 
            //�@@�@@l_row�F�@@�X�V��̃f�[�^���Z�b�g�����C���X�^���X
            
            WEB3DataAccessUtility.updateRow(l_fxAccountParams, l_hmUpdateValues);
        
        }
        catch (DataException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (updateFX�ڋq) <BR>
     * �X�V��̒l��FX�ڋq�e�[�u���̃��[���A�h���X���X�V����B <BR>
     * <BR>
     * �@@�@@(1)FX�ڋqParams�C���X�^���X���擾����B<BR>
     * �@@�@@�@@���L�����ɂ�FX�ڋq�e�[�u������������B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F ����.FX�ڋqParams.�،���ЃR�[�h<BR>
     * �@@�@@�@@���X�R�[�h�F ����.FX�ڋqParams.���X�R�[�h<BR>
     * �@@�@@�@@�ڋq�R�[�h ����.FX�ڋqParams.�ڋq�R�[�h<BR>
     *       FX�V�X�e���R�[�h IN ����.FX�V�X�e���R�[�h�ꗗ<BR>
     * <BR>
     * �@@�@@�@@��null���ԋp���ꂽ�ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �@@�@@(2)(1)�Ŏ擾����FX�ڋq�̑S���R�[�h�ɑ΂��A���L�X�V�d�l�̒ʂ�DB�X�V���s���B<BR>
     * <BR>
     * �@@�@@�@@�y��Trade�z�⑫����.DB�X�V<BR>
     * �@@�@@�@@�u�Ǘ��ҁE�����Ǘ�_FX�ڋq�e�[�u��DB�X�V�d�l.xls�v��<BR>
     * �@@�@@�@@�u(�����Ǘ�)FX�ڋq�e�[�u��DB�X�V�d�l(���[���A�h���X�X�V)�v�V�[�g<BR>
     * @@param l_fxAccountParams - FX�ڋqParam�I�u�W�F�N�g
     * @@param l_strUpdatedMailAddress - �X�V�チ�[���A�h���X
     * @@param l_strUpdaterCode - �X�V�҃R�[�h
     * @@param l_fxSystemCodeList - FX�V�X�e���R�[�h�ꗗ
     * @@throws WEB3BaseException
     */
     public void updateFXAccount(FxAccountParams l_fxAccountParams,
        String l_strUpdatedMailAddress, String l_strUpdaterCode, 
		ArrayList l_fxSystemCodeList) throws WEB3BaseException
    {
     	
        String STR_METHOD_NAME =
            "updateFXAccount(FxAccountParams l_fxAccountParams," +
            "String l_strUpdatedMailAddress, " +
            "String l_strUpdaterCode," +
			"ArrayList l_fxSystemCodeList)";
        log.entering(STR_METHOD_NAME);
     	
        //(1)FX�ڋqParams�C���X�^���X���擾����B
        //���L�����ɂ�FX�ڋq�e�[�u������������B
        //[����]
        //�،���ЃR�[�h�F ����.FX�ڋqParams.�،���ЃR�[�h
        //���X�R�[�h�F ����.FX�ڋqParams.���X�R�[�h
        //�ڋq�R�[�h ����.FX�ڋqParams.�ڋq�R�[�h
        //FX�V�X�e���R�[�h IN ����.FX�V�X�e���R�[�h�ꗗ
        String l_strWhere =
            " institution_code = ? and branch_code = ? and account_code = ? ";
        
		List l_lisValues = new ArrayList();
		l_lisValues.add(l_fxAccountParams.getInstitutionCode());
		l_lisValues.add(l_fxAccountParams.getBranchCode());
		l_lisValues.add(l_fxAccountParams.getAccountCode());
        
		if(l_fxSystemCodeList != null && l_fxSystemCodeList.size() != 0){
			
			l_strWhere += "and fx_system_code in (";
	        
			for(int i=0; i<l_fxSystemCodeList.size(); i++){
				l_lisValues.add(l_fxSystemCodeList.get(i));
                if (i == l_fxSystemCodeList.size() - 1)
                {
                    l_strWhere += " ?";
                }
                else
                {
                    l_strWhere += " ?,";
                }
	          	
	        }
			l_strWhere += " ) ";
		}
		
		Object[] l_objValue = new Object[l_lisValues.size()];
		l_lisValues.toArray(l_objValue);

        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    FxAccountRow.TYPE,
                    l_strWhere,
                    l_objValue);
        }
        catch (DataQueryException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        Iterator l_iterator = l_lisRows.iterator();

            while (l_iterator.hasNext())
            {
                FxAccountRow l_fxAccountRow = (FxAccountRow)l_iterator.next();
                FxAccountParams l_Params = new FxAccountParams(l_fxAccountRow);
                try
                {
                    //(2)(1)�Ŏ擾����FX�ڋq�̑S���R�[�h�ɑ΂��A���L�X�V�d�l�̒ʂ�DB�X�V���s���B
                    //�y��Trade�z�⑫����.DB�X�V
                    //�u�Ǘ��ҁE�����Ǘ�_FX�ڋq�e�[�u��DB�X�V�d�l.xls�v��
                    //�u(�����Ǘ�)FX�ڋq�e�[�u��DB�X�V�d�l(���[���A�h���X�X�V)�v�V�[�g
                    //FX���[���A�h���X
                    //����:����.�X�V�チ�[���A�h���X
                    l_Params.setFxMailAddress(l_strUpdatedMailAddress);
                    //�X�V�҃R�[�h
                    //����.�X�V�҃R�[�h
                    l_Params.setLastUpdater(l_strUpdaterCode);
                    //�X�V���t
                    //����:���ݎ���
                    l_Params.setLastUpdatedTimestamp(
                        GtlUtils.getSystemTimestamp());

                    WEB3DataAccessUtility.updateRow(l_Params);
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
            }
        
        log.exiting(STR_METHOD_NAME);
    
    }

    /**
     * (updateFX�ڋq���[���A�h���X)<BR>
     * �X�V��̒l��FX�ڋq�e�[�u���̃��[���A�h���X���X�V����B<BR>
     * <BR>
     * FX�ڋq�̃��R�[�h�ɑ΂��A���L�X�V�d�l�̒ʂ�DB�X�V���s��<BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V<BR>
     * �u�Ǘ��ҁE�����Ǘ�_FX�ڋq�e�[�u��DB�X�V�d�l.xls�v��<BR>
     * �u(�����Ǘ�)FX�ڋq�e�[�u��DB�X�V�d�l(���[���A�h���X�X�V)�v�V�[�g<BR>
     * <BR>
     * @@param l_fxAccountParams - (FX�ڋqParams)<BR>
     * FX�ڋqParam�I�u�W�F�N�g<BR>
     * @@param l_strUpdatedMailAddress - (�X�V�チ�[���A�h���X)<BR>
     * �X�V�チ�[���A�h���X<BR>
     * @@param l_strUpdaterCode - (�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h<BR>
     * @@throws WEB3BaseException
     */
    public void updateFXAccountMailAddress(
        FxAccountParams l_fxAccountParams,
        String l_strUpdatedMailAddress,
        String l_strUpdaterCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateFXAccountMailAddress(FxAccountParams, String, String)";
        log.entering(STR_METHOD_NAME);

        if(l_fxAccountParams == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�u�Ǘ��ҁE�����Ǘ�_FX�ڋq�e�[�u��DB�X�V�d�l.xls�v��
        //�u(�����Ǘ�)FX�ڋq�e�[�u��DB�X�V�d�l(���[���A�h���X�X�V)�v�V�[�g
        HashMap l_hmUpdateValues = new HashMap();
        try
        {
            //FX���[���A�h���X
            //����:����.�X�V�チ�[���A�h���X
            l_hmUpdateValues.put("fx_mail_address", l_strUpdatedMailAddress);

            //�X�V�҃R�[�h
            //����.�X�V�҃R�[�h
            l_hmUpdateValues.put("last_updater", l_strUpdaterCode);

            //�X�V���t
            //����:���ݎ���
            l_hmUpdateValues.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

            WEB3DataAccessUtility.updateRow(l_fxAccountParams, l_hmUpdateValues);
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
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (updateFX�����ԍ�) <BR>
     * �X�V��̒l��FX�����ԍ��e�[�u�����X�V����B <BR>
     * <BR>
     * �P�jFX�����ԍ�Params�C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �Q�j���������C���X�^���X�ɁA�p�����[�^.FX�����ԍ�Param�� <BR>
     * �v���p�e�B���R�s�[����B <BR>
     * <BR>
     * �R�j���������C���X�^���X�ɍX�V��̃f�[�^���Z�b�g����B <BR>
     * �X�V����s�̓��e�͉��L���Q�ƁB <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �u�Ǘ��ҁE�����Ǘ�_FX�����ԍ��e�[�u��DB�X�V�d�l.xls�v <BR>
     * <BR>
     * �S�jFX�����ԍ���update <BR>
     * WEB3DataAccessUtility.updateRow()���\�b�h���R�[������B <BR>
     * 
     * [updateRow()�ɃZ�b�g����p�����[�^] l_row�F �X�V��̃f�[�^���Z�b�g�����C���X�^���X
     * 
     * @@param l_fxAccountCodeParams - FX�����ԍ�Param�I�u�W�F�N�g
     * @@param l_strUpdatedAccountCode - �X�V���FX�����ԍ�
     * @@param l_strUpdaterCode - �X�V�҃R�[�h
     * @@throws WEB3BaseException
     * @@roseuid 41C80E2B018C
     */
    public void updateFXAccountCode(FxAccountCodeParams l_fxAccountCodeParams,
        String l_strUpdatedAccountCode, String l_strUpdaterCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateFXAccountCode(FxAccountCodeParams l_fxAccountCodeParams," +
            "String l_strUpdatedAccountCode, String l_strUpdaterCode) ";
        log.entering(STR_METHOD_NAME);
        
        if(l_fxAccountCodeParams == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        {
            //�P�jFX�����ԍ�Params�C���X�^���X�𐶐�����B
            //�Q�j���������C���X�^���X�ɁA�p�����[�^.FX�����ԍ�Param�� 
            //�@@�v���p�e�B���R�s�[����B 
            FxAccountCodeParams l_fxAccountCodeParamsUpdate = new FxAccountCodeParams(l_fxAccountCodeParams);
            
            //�R�j���������C���X�^���X�ɍX�V��̃f�[�^���Z�b�g����B 
            //�@@�X�V����s�̓��e�͉��L���Q�ƁB
            //�@@�y��Trade�z�⑫����.DB�X�V 
            //�@@�u�Ǘ��ҁE�����Ǘ�_FX�����ԍ��e�[�u��DB�X�V�d�l.xls�v
            
            //3.1) FX�����ԍ�
            //���� : �X�V������ԍ�
            l_fxAccountCodeParamsUpdate.setFxAccountCode(l_strUpdatedAccountCode);
            
            //3.2) �X�V�҃R�[�h
            //���� : ����.�X�V�҃R�[�h
            l_fxAccountCodeParamsUpdate.setLastUpdater(l_strUpdaterCode);
            
            //3.3) �X�V���t
            //���� : ���ݎ���
            l_fxAccountCodeParamsUpdate.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
    
            
            //�S�jFX�����ԍ���update 
            //WEB3DataAccessUtility.updateRow()���\�b�h���R�[������B 
            //
            //�@@[updateRow()�ɃZ�b�g����p�����[�^] 
            //�@@�@@l_row�F�@@�X�V��̃f�[�^���Z�b�g�����C���X�^���X 
            WEB3DataAccessUtility.updateRow(l_fxAccountCodeParamsUpdate);
        
        }
        catch (DataException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (updateGFT�����J�ݏ�) <BR>
     * �X�V��̒l��GFT�����J�ݏ󋵃e�[�u�����X�V����B <BR>
     * <BR>
     * �P�jGFT�����J�ݏ�Params�C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �Q�j���������C���X�^���X�ɁA�p�����[�^.GFT�����J�ݏ�Param�� <BR>
     * �v���p�e�B���R�s�[����B <BR>
     * <BR>
     * �R�j���������C���X�^���X�ɍX�V��̃f�[�^���Z�b�g����B <BR>
     * �X�V����s�̓��e�͉��L���Q�ƁB <BR>
     * <BR>
     * [�p�����[�^.�X�V��X�e�[�^�X�敪 == "1�F�����J�݊���"�̏ꍇ] <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �u�Ǘ��ҁE�����J�݊Ǘ�_GFT�����J�ݏ󋵃e�[�u��DB�X�V�d�l.xls�v <BR>
     * �́u(�����J�݊Ǘ�)[�����J�݊���]GFT�����J�ݏ󋵃e�[�u���v�V�[�g <BR>
     * <BR>
     * [�p�����[�^.�X�V��X�e�[�^�X�敪 == "9�F�폜"�̏ꍇ] <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �u�Ǘ��ҁE�����J�݊Ǘ�_GFT�����J�ݏ󋵃e�[�u��DB�X�V�d�l.xls�v <BR>
     * �́u(�����J�݊Ǘ�)[�폜]GFT�����J�ݏ󋵃e�[�u���v�V�[�g <BR>
     * <BR>
     * [�p�����[�^.�X�V��X�e�[�^�X�敪�����ݒ�i=null�j�̏ꍇ]<BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �u�Ǘ��ҁE�����J�݊Ǘ�_GFT�����J�ݏ󋵃e�[�u��DB�X�V�d�l.xls�v<BR>
     * �́u(�����J�݊Ǘ�)[������X�V]GFT�����J�ݏ󋵃e�[�u���v�V�[�g<BR>
     * <BR>
     * �S�jGFT�����J�ݏ󋵂�
     *    update WEB3DataAccessUtility.updateRow()���\�b�h���R�[������B<BR>
     * <BR>
     * [updateRow()�ɃZ�b�g����p�����[�^] l_row�F �X�V��̃f�[�^���Z�b�g�����C���X�^���X
     * 
     * @@param l_gftAccountOpenStatusParams - GFT�����J�ݏ�Params�I�u�W�F�N�g
     * @@param l_strUpdatedStatusDiv - �X�V��X�e�[�^�X�敪
     * @@param l_updatedFxAccInformationUnits - �X�V���FX�������̔z��
     * @@param l_strUpdaterCode - �X�V�҃R�[�h
     * @@param l_strUpdatedAgreementDiv - (�X�V�������敪)<BR>
     * �X�V�������敪<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41BE6F0300EE
     */
    public void updateGFTAccountOpenStatus(
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams,
        String l_strUpdatedStatusDiv,
        WEB3FXAccInformationUnit[] l_updatedFxAccInformationUnits,
        String l_strUpdaterCode, 
        String l_strUpdatedAgreementDiv) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateGFTAccountOpenStatus(GftAccountOpenStatusParams l_gftAccountOpenStatusParams," +
            "String l_strUpdatedStatusDiv, WEB3FXAccInformationUnit[] l_updatedFxAccInformation," +
            "String l_strUpdaterCode, String l_strUpdatedAgreementDiv)";
        log.entering(STR_METHOD_NAME);
        
        if(l_gftAccountOpenStatusParams == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        {
        
            //�P�jGFT�����J�ݏ�Params�C���X�^���X�𐶐�����B 
            //�Q�j���������C���X�^���X�ɁA�p�����[�^.GFT�����J�ݏ�Param�� 
            //�@@�v���p�e�B���R�s�[����B 
            GftAccountOpenStatusParams l_paramsForUpdate =  
                new GftAccountOpenStatusParams(l_gftAccountOpenStatusParams);
            
            //�R�j���������C���X�^���X�ɍX�V��̃f�[�^���Z�b�g����B 
            //�@@�X�V����s�̓��e�͉��L���Q�ƁB
            //�@@[�p�����[�^.�X�V��X�e�[�^�X�敪 == "1�F�����J�݊���"�̏ꍇ] 
            //�@@�@@�y��Trade�z�⑫����.DB�X�V 
            //�@@�@@�u�Ǘ��ҁE�����J�݊Ǘ�_GFT�����J�ݏ󋵃e�[�u��DB�X�V�d�l.xls�v 
            //�@@�@@�́u(�����J�݊Ǘ�)[�����J�݊���]GFT�����J�ݏ󋵃e�[�u���v�V�[�g
            if(WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_COMPLETE.equals(l_strUpdatedStatusDiv))
            {
                //3.1) �����p�X���[�h
                //���� : null
                l_paramsForUpdate.setPassword(null);
                
                for(int j = 0; j < l_updatedFxAccInformationUnits.length; j++)
                {
                    //3.2) �����ԍ��i1���ʉ݃R�[�X�j
                    //���� : �X�V��FX�������ꗗ�̂����A
                    //       �R�[�X�敪 == "1���ʉ݃R�[�X"�ł���FX�������.�����ԍ�
                    if(WEB3GftTransStatusCourseDivDef.ONE_COSE.equals(l_updatedFxAccInformationUnits[j].fxCourseDiv))
                    {
                        l_paramsForUpdate.setFxAccountCode01(l_updatedFxAccInformationUnits[j].fxAccountCode);
                    }

                    //3.3) �����ԍ��i10���ʉ݃R�[�X�j
                    //���� : �X�V��FX�������ꗗ�̂����A
                    //       �R�[�X�敪 == "10���ʉ݃R�[�X"�ł���FX�������.�����ԍ�
                    if(WEB3GftTransStatusCourseDivDef.TEN_COSE.equals(l_updatedFxAccInformationUnits[j].fxCourseDiv))
                    {
                        l_paramsForUpdate.setFxAccountCode10(l_updatedFxAccInformationUnits[j].fxAccountCode);
                    }

                    //3.8) �A�g�p�����ԍ�
                    //�X�V��FX�������ꗗ�̂����A
                    //�@@�R�[�X�敪 == "CFD�R�[�X"�ł���FX�������.�����ԍ�
                    if (WEB3GftTransStatusCourseDivDef.CFD_COURSE.equals(
                        l_updatedFxAccInformationUnits[j].fxCourseDiv))
                    {
                        l_paramsForUpdate.setExtAccountCode(
                            l_updatedFxAccInformationUnits[j].fxAccountCode);
                    }
                }
                
                //3.4) �����J�ݏ󋵋敪
                //���� : 1�F�����J�݊���
                l_paramsForUpdate.setAccountOpenStatusDiv(WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_COMPLETE);
                
                //3.5) �X�V�҃R�[�h
                //���� : ����.�X�V�҃R�[�h
                l_paramsForUpdate.setLastUpdater(l_strUpdaterCode);
                
                //3.6) �X�V���t
                //���� : ���ݎ���
                l_paramsForUpdate.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());            
                
                //3.7) ������敪
                //���� : �X�V�������敪==null �̏ꍇ�i�����l�j
                //�ȊO�A�X�V�������敪
                if (l_strUpdatedAgreementDiv != null)
                {
                    l_paramsForUpdate.setAgreementDiv(l_strUpdatedAgreementDiv);
                }
            }
            
            //�@@[�p�����[�^.�X�V��X�e�[�^�X�敪 == "9�F�폜"�̏ꍇ] 
            //�@@�@@�y��Trade�z�⑫����.DB�X�V 
            //�@@�@@�u�Ǘ��ҁE�����J�݊Ǘ�_GFT�����J�ݏ󋵃e�[�u��DB�X�V�d�l.xls�v 
            //�@@�@@�́u(�����J�݊Ǘ�)[�폜]GFT�����J�ݏ󋵃e�[�u���v�V�[�g 
            if(WEB3AccountOpenStatusDivDef.DELETE.equals(l_strUpdatedStatusDiv))
            {
    
                //3.1)�����p�X���[�h
                //���� : null
                l_paramsForUpdate.setPassword(null);
                
                //3.2) �����ԍ��i1���ʉ݃R�[�X�j
                //���� : null
                l_paramsForUpdate.setFxAccountCode01(null);
                
                //3.3) �����ԍ��i10���ʉ݃R�[�X�j
                //���� : null
                l_paramsForUpdate.setFxAccountCode10(null);
    
                
                //3.4) �����J�ݏ󋵋敪
                //���� : 9�F�폜
                l_paramsForUpdate.setAccountOpenStatusDiv(WEB3AccountOpenStatusDivDef.DELETE);
                
                //3.5) �X�V�҃R�[�h
                //���� : ����.�X�V�҃R�[�h
                l_paramsForUpdate.setLastUpdater(l_strUpdaterCode);
                
                //3.6) �X�V���t
                //���� : ���ݎ���
                l_paramsForUpdate.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());            

                //3.7) �A�g�p�����ԍ�
                //���� : null
                l_paramsForUpdate.setExtAccountCode(null);
            }
            
            //�@@[�p�����[�^.�X�V��X�e�[�^�X�敪�����ݒ�i=null�j�̏ꍇ]
            //   �y��Trade�z�⑫����.DB�X�V 
            //   �u�Ǘ��ҁE�����J�݊Ǘ�_GFT�����J�ݏ󋵃e�[�u��DB�X�V�d�l.xls�v
            //   �́u(�����J�݊Ǘ�)[������X�V]GFT�����J�ݏ󋵃e�[�u���v�V�[�g
            if(l_strUpdatedStatusDiv == null)
            {
                //3.1) �X�V�҃R�[�h
                //���� : ����.�X�V�҃R�[�h
                l_paramsForUpdate.setLastUpdater(l_strUpdaterCode);
                
                //3.2) �X�V���t
                //���� : ���ݎ���
                l_paramsForUpdate.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                
                //3.2) ������敪
                //���� : �X�V�������敪
                if(l_strUpdatedAgreementDiv != null)
                {
                    l_paramsForUpdate.setAgreementDiv(l_strUpdatedAgreementDiv);
                }
                else
                {
                    log.debug("�p�����[�^�l�s���B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + "." + STR_METHOD_NAME, 
                        "�p�����[�^�l�s���B");
                }
            }
            
            //�S�jGFT�����J�ݏ󋵂�update 
            //WEB3DataAccessUtility.updateRow()���\�b�h���R�[������B 
            //
            //�@@[updateRow()�ɃZ�b�g����p�����[�^] 
            //�@@�@@l_row�F�@@�X�V��̃f�[�^���Z�b�g�����C���X�^���X
            WEB3DataAccessUtility.updateRow(l_paramsForUpdate);
        
        }
        catch (DataException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (updateGFT�����J�ݏ�) <BR>
     * GFT���ʒʒm�̏�Ԃ�GFT�����J�ݏ󋵃e�[�u���̃f�[�^��update����B <BR>
     * <BR>
     * �P�jGFT�����J�ݏ�Params�C���X�^���X���쐬����B <BR>
     * <BR>
     * �Q�j���������C���X�^���X�ɁA����.GFT�����J�ݏ󋵂̃v���p�e�B���R�s�[����B <BR>
     * <BR>
     * �R�j���������C���X�^���X�ɁA�X�V��̃f�[�^���Z�b�g����B <BR>
     * <BR>
     * �X�V����s�̓��e�͉��L���Q�ƁB <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �uFX�����J��_GFT�����J�ݏ󋵃e�[�u��.xls�v�� <BR>
     * �uGFT�����J�ݏ󋵃e�[�u��_DB�X�V�d�l_[���ʒʒm]�v�V�[�g <BR>
     * <BR>
     * �S�jGFT�����J�ݏ󋵂�update <BR>
     * WEB3DataAccessUtility.updateRow()���\�b�h���R�[������B <BR>
     * <BR>
     * [updateRow()�ɃZ�b�g����p�����[�^] l_row�F <BR>
     * �@@�@@l_row�F�@@�R�j�ɂčX�V��̃f�[�^���Z�b�g�����C���X�^���X<BR>
     * <BR>
     * @@param l_gftAccountOpenStatusParams - GFT�����J�ݏ�Params�I�u�W�F�N�g
     * @@param l_fxGftResultNoticeTelegramUnit - GFT���ʒʒm�d������
     * @@param l_strErrorReasonCode - �G���[���R�R�[�h
     * @@throws WEB3BaseException
     * @@roseuid 41C97A0D02E8
     */
    public void updateGFTAccountOpenStatus(
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams,
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit,
        String l_strErrorReasonCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateGFTAccountOpenStatus(GftAccountOpenStatusParams l_gftAccountOpenStatusParams, " +
            "WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit," +
            "String l_strErrorReasonCode)";
        log.entering(STR_METHOD_NAME);
        
        if(l_gftAccountOpenStatusParams == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�jGFT�����J�ݏ�Params�C���X�^���X���쐬����B
        //�Q�j���������C���X�^���X�ɁA����.GFT�����J�ݏ󋵂̃v���p�e�B���R�s�[����B
        GftAccountOpenStatusParams l_paramsForUpdate =
            new GftAccountOpenStatusParams(l_gftAccountOpenStatusParams);
        
        //�R�j���������C���X�^���X�ɁA�X�V��̃f�[�^���Z�b�g����B
            //�X�V����s�̓��e�͉��L���Q�ƁB
            //�y��Trade�z�⑫����.DB�X�V
            //�uFX�����J��_GFT�����J�ݏ󋵃e�[�u��.xls�v��
            //�uGFT�����J�ݏ󋵃e�[�u��_DB�X�V�d�l_[���ʒʒm]�v�V�[�g

            //3.1) �����p�X���[�h
            //���� : null
            l_paramsForUpdate.setPassword(null);

            //3.2) �����ԍ��i1���ʉ݃R�[�X�j
            //���� : ����.�G���[���R�R�[�h��0000�i����j�̊��A
            //����.GFT���ʒʒm����.�ב֕ۏ؋��������ꗗ[n].�R�[�X�敪==�h1�h�̏ꍇ:
            //����.GFT���ʒʒm�d������.�ב֕ۏ؋������ԍ�(1���ʉ�)
            //��L�ȊO�̏ꍇ�F�i�����l�j
            //3.3) �����ԍ��i10���ʉ݃R�[�X�j
            //���� : ����.�G���[���R�R�[�h��0000�i����j���A
            //����.GFT���ʒʒm����.�ב֕ۏ؋��������ꗗ[n].�R�[�X�敪==�h2�h�̏ꍇ�F
            //����.GFT���ʒʒm�d������.�ב֕ۏ؋������ԍ�(10���ʉ�)
            //��L�ȊO�̏ꍇ�F�i�����l�j
            //3.4) �����J�ݏ󋵋敪
            //���� : ����.�G���[���R�R�[�h��0000�i����j�̏ꍇ�F1�i�����J�݊����j
            //��L�ȊO�̏ꍇ�F2�i�����J�݃G���[�j

            if(WEB3GftErrorReasonCodeDef.NORMAL.equals(l_strErrorReasonCode))
            {
                WEB3FXAccInformationUnit[] l_fXAccInformationUnits =
                    l_fxGftResultNoticeTelegramUnit.fxAccInformationList;
                int l_intFXAccInformationUnitsLength = 0;
                if (l_fXAccInformationUnits != null)
                {
                    l_intFXAccInformationUnitsLength = l_fXAccInformationUnits.length;
                }
                for (int i = 0; i < l_intFXAccInformationUnitsLength; i++)
                {

                    if (WEB3GftTransStatusCourseDivDef.ONE_COSE.equals(
                        l_fXAccInformationUnits[i].fxCourseDiv))
                    {
                        l_paramsForUpdate.setFxAccountCode01(
                            l_fXAccInformationUnits[i].fxAccountCode);
                    }

                    if (WEB3GftTransStatusCourseDivDef.TEN_COSE.equals(
                        l_fXAccInformationUnits[i].fxCourseDiv))
                    {
                        l_paramsForUpdate.setFxAccountCode10(
                            l_fXAccInformationUnits[i].fxAccountCode);
                    }

                    //3.9) �A�g�p�����ԍ�
                    //���� :
                    //����.�G���[���R�R�[�h��0000�i����j���A
                    //����.GFT���ʒʒm����.�ב֕ۏ؋��������ꗗ[n].�R�[�X�敪==�h3�h�̏ꍇ�F
                    //       ����.GFT���ʒʒm�d������.�ב֕ۏ؋������ԍ�(CFD�R�[�X)
                    //       ��L�ȊO�̏ꍇ�F�i�����l�j

                    if (WEB3GftTransStatusCourseDivDef.CFD_COURSE.equals(
                            l_fXAccInformationUnits[i].fxCourseDiv))
                    {
                        l_paramsForUpdate.setExtAccountCode(
                            l_fXAccInformationUnits[i].fxAccountCode);
                    }
                }

                l_paramsForUpdate.setAccountOpenStatusDiv(
                    WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_COMPLETE);
            }
            else
            {
                l_paramsForUpdate.setAccountOpenStatusDiv(
                    WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_ERROR);
            }

            //3.5) ����M�敪
            //���� :
            //����.�G���[���R�R�[�h���i0000�i����j�A�܂��́A0004�i��t���ʃR�[�h�`�F�b�N�G���[�j�j�̏ꍇ�F
            //2�i��M�ρj
            //��L�ȊO�̏ꍇ�F3�i��M�G���[�j
            if(WEB3GftErrorReasonCodeDef.NORMAL.equals(l_strErrorReasonCode)
                || WEB3GftErrorReasonCodeDef.RESULT_CODE_ERROR.equals(
                    l_strErrorReasonCode) )
            {
                l_paramsForUpdate.setSendRcvDiv(
                    WEB3SendRcvDivDef.RECEIVE_COMPLETE);
            }
            else
            {
                l_paramsForUpdate.setSendRcvDiv(
                    WEB3SendRcvDivDef.RECEIVE_ERROR);
            }

            //3.6) ��t���ʃR�[�h
            //���� : ����.GFT���ʒʒm�d������.��t����
            l_paramsForUpdate.setResultCode(
                l_fxGftResultNoticeTelegramUnit.resultCode);

            //3.7) �G���[���R�R�[�h
            //���� : ����.�G���[���R�R�[�h
            l_paramsForUpdate.setErrorReasonCode(l_strErrorReasonCode);

            //3.8) �X�V���t
            //���� : ���ݎ���
            l_paramsForUpdate.setLastUpdatedTimestamp(
                GtlUtils.getSystemTimestamp());

        //�S�jGFT�����J�ݏ󋵂�update
        //WEB3DataAccessUtility.updateRow()���\�b�h���R�[������B
        //[updateRow()�ɃZ�b�g����p�����[�^]
        //l_row�F�@@�R�j�ɂčX�V��̃f�[�^���Z�b�g�����C���X�^���X
        try
        {
            WEB3DataAccessUtility.updateRow(l_paramsForUpdate);
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

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (updateGFT�U�֏�) <BR>
     * ������GFT�U�֏�Params��GFT�U�֏󋵃e�[�u�����X�V����B <BR>
     * <BR>
     * �P�jQueryProcessor.doUpdateQuery()���\�b�h���R�[������B <BR>
     * <BR>
     * [doUpdateQuery()�ɃZ�b�g����p�����[�^] <BR>
     * arg0�F �p�����[�^.GFT�U�֏�Params <BR>
     * @@param l_gftTransferStatusParams - GFT�U�֏�Params�I�u�W�F�N�g
     * @@throws WEB3BaseException
     * @@roseuid 41C1301F0156
     */
    public void updateGFTTransferStatus(
        GftTransferStatusParams l_gftTransferStatusParams) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateGFTTransferStatus(GftTransferStatusParams l_gftTransferStatusParams)";
        log.entering(STR_METHOD_NAME);
        
        if(l_gftTransferStatusParams == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.debug("l_gftTransferStatusParams = " + l_gftTransferStatusParams);
        //�P�jQueryProcessor.doUpdateQuery()���\�b�h���R�[������B
        //�@@[doUpdateQuery()�ɃZ�b�g����p�����[�^] 
        //�@@�@@arg0�F�@@�p�����[�^.GFT�U�֏�Params
        try
        {
            Processors.getDefaultProcessor().doUpdateQuery(l_gftTransferStatusParams);
        }
        catch (DataException l_ex)
        {
            log.debug("__DataException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (updateGFT�U�֏�) <BR>
     * GFT���ʒʒm�̏�Ԃ�GFT�U�֏󋵃e�[�u���̃f�[�^��update����B <BR>
     * <BR>
     * �P�jGFT�U�֏�Params�C���X�^���X���쐬����B <BR>
     * <BR>
     * �Q�j���������C���X�^���X�ɁA����.GFT�U�֏󋵂̃v���p�e�B���R�s�[����B <BR>
     * <BR>
     * �R�j���������C���X�^���X�ɁA�X�V��̃f�[�^���Z�b�g����B <BR>
     * <BR>
     * �X�V����s�̓��e�͉��L���Q�ƁB <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �u�U�֋���_GFT�U�֏󋵃e�[�u��.xls�v�� <BR>
     * �uGFT�U�֏󋵃e�[�u��_DB�X�V�d�l_[���ʒʒm]�v�V�[�g <BR>
     * <BR>
     * �S�jGFT�U�֏󋵂�update <BR>
     * WEB3DataAccessUtility.updateRow()���\�b�h���R�[������B <BR>
     * <BR>
     * [updateRow()�ɃZ�b�g����p�����[�^] <BR>
     * l_row�F �R�j�ɂčX�V��̃f�[�^���Z�b�g�����C���X�^���X <BR>
     * 
     * @@param l_gftTransferStatusParams - GFT�U�֏�Params�I�u�W�F�N�g
     * @@param l_fxGftResultNoticeTelegramUnit - GFT���ʒʒm�d������
     * @@param l_strUpdatedTransferDate - YYYYMMDD
     * �X�V���n�\���
     * ����n�\������X�V���Ȃ��ꍇ��null
     * @@param l_strErrorReasonCode - �G���[���R�R�[�h
     * @@throws WEB3BaseException
     * @@roseuid 41C18781002B
     */
    public void updateGFTTransferStatus(
        GftTransferStatusParams l_gftTransferStatusParams,
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit,
        String l_strUpdatedTransferDate, String l_strErrorReasonCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateGFTTransferStatus(GftTransferStatusParams l_gftTransferStatusParams, " +
            "WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit," +
            "String l_strUpdatedTransferDate, String l_strErrorReasonCode)";
        log.entering(STR_METHOD_NAME);
        
        if(l_gftTransferStatusParams == null || l_fxGftResultNoticeTelegramUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�jGFT�U�֏�Params�C���X�^���X���쐬����B 
        //�Q�j���������C���X�^���X�ɁA����.GFT�U�֏󋵂̃v���p�e�B���R�s�[����B
        GftTransferStatusParams l_gftTransferStatusParamsForUpdate = 
            new GftTransferStatusParams(l_gftTransferStatusParams);
        
        //�R�j���������C���X�^���X�ɁA�X�V��̃f�[�^���Z�b�g����B 
        //�@@�X�V����s�̓��e�͉��L���Q�ƁB 
        //�@@�y��Trade�z�⑫����.DB�X�V 
        //�@@�u�U�֋���_GFT�U�֏󋵃e�[�u��.xls�v�� 
        //�@@�uGFT�U�֏󋵃e�[�u��_DB�X�V�d�l_[���ʒʒm]�v�V�[�g
        try
        {
            //3.1) ��n�\���
            //���� : ����.�X�V���n���\��� != null�̏ꍇ�A����.�X�V���n�\���
            //�ȊO�A�i�����l�j
            if(l_strUpdatedTransferDate != null)
            {
                l_gftTransferStatusParamsForUpdate.setTransferDate(l_strUpdatedTransferDate);
            }
            
            //3.2) �U�֏󋵋敪
            //���� : ����.�G���[���R�R�[�h��0000�i����j�̏ꍇ�F1�i���ϊ����j
            //��L�ȊO�̏ꍇ�F2�i���σG���[�j
            if(WEB3GftErrorReasonCodeDef.NORMAL.equals(l_strErrorReasonCode))
            {
                l_gftTransferStatusParamsForUpdate.setTransferStatusDiv(WEB3TransferStatusDivDef.PROCESS_COMPLETE);
            }
            else
            {
                l_gftTransferStatusParamsForUpdate.setTransferStatusDiv(WEB3TransferStatusDivDef.PROCESS_ERROR);
            }
            
            //3.3) ����M�敪
            //���� : ����.�G���[���R�R�[�h���i0000�i����j�A�܂��́A0004�i��t���ʃR�[�h�`�F�b�N�G���[�j�j�̏ꍇ�F2�i��M�ρj
            //��L�ȊO�̏ꍇ�F3�i��M�G���[�j
            if(WEB3GftErrorReasonCodeDef.NORMAL.equals(l_strErrorReasonCode) 
                || WEB3GftErrorReasonCodeDef.RESULT_CODE_ERROR.equals(l_strErrorReasonCode) )
            {
                l_gftTransferStatusParamsForUpdate.setSendRcvDiv(WEB3SendRcvDivDef.RECEIVE_COMPLETE);    
            }
            else
            {
                l_gftTransferStatusParamsForUpdate.setSendRcvDiv(WEB3SendRcvDivDef.RECEIVE_ERROR);
            }
    
            //3.4) ��t���ʃR�[�h
            //���� : ����.GFT���ʒʒm�d������.��t����
            l_gftTransferStatusParamsForUpdate.setResultCode(l_fxGftResultNoticeTelegramUnit.resultCode);
    
            //3.5) �G���[���R�R�[�h
            //���� : ����.�G���[���R�R�[�h
            l_gftTransferStatusParamsForUpdate.setErrorReasonCode(l_strErrorReasonCode);
            
            //3.6) �������ԁi��M�j
            //���� : ����.GFT���ʒʒm�d������.GFT��DIR���M����
            l_gftTransferStatusParamsForUpdate.setReceiveTime(l_fxGftResultNoticeTelegramUnit.gftSendTime);
            
            
            //3.7) �X�V���t
            //���� : ���ݎ���
            l_gftTransferStatusParamsForUpdate.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            //�S�jGFT�U�֏󋵂�update 
            //WEB3DataAccessUtility.updateRow()���\�b�h���R�[������B 
            //
            //�@@[updateRow()�ɃZ�b�g����p�����[�^] 
            //�@@�@@l_row�F�@@�R�j�ɂčX�V��̃f�[�^���Z�b�g�����C���X�^���X
            //insert GFT�U�֏󋵃e�[�u��
            
            WEB3DataAccessUtility.updateRow(l_gftTransferStatusParamsForUpdate);
        
        }
        catch (DataException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (updateGFT�U�֏�) <BR>
     * GFT�U�֏󋵃e�[�u���̐U�֏󋵋敪��update����B <BR>
     * <BR>
     * �P�jGFT�U�֏�Params�C���X�^���X���쐬����B <BR>
     * <BR>
     * �Q�j���������C���X�^���X�ɁA����.GFT�U�֏󋵂̃v���p�e�B���R�s�[����B <BR>
     * <BR>
     * �R�j���������C���X�^���X�ɁA�X�V��̃f�[�^���Z�b�g����B <BR>
     * <BR>
     * �X�V����s�̓��e�͉��L���Q�ƁB <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �uFX�U�֋���_GFT�U�֏󋵃e�[�u��.xls�v�� <BR>
     * �uGFT�U�֏󋵃e�[�u��_DB�X�V�d�l[�ۏ؋��U�֊Ǘ�]�v�V�[�g <BR>
     * <BR>
     * �S�jGFT�U�֏󋵂�update <BR>
     * WEB3DataAccessUtility.updateRow()���\�b�h���R�[������B <BR>
     * <BR>
     * [updateRow()�ɃZ�b�g����p�����[�^] <BR>
     * l_row�F �R�j�ɂčX�V��̃f�[�^���Z�b�g�����C���X�^���X <BR>
     * @@param l_gftTransferStatusParams - GFT�U�֏�Params�I�u�W�F�N�g
     * @@param l_strUpdatedTransferStatusDiv - �X�V��̐U�֏󋵋敪
     * @@param l_strUpdaterCode - �X�V�҃R�[�h
     * @@throws WEB3BaseException
     * @@roseuid 41CBC3DD00B3
     */
    public void updateGFTTransferStatus(
        GftTransferStatusParams l_gftTransferStatusParams,
        String l_strUpdatedTransferStatusDiv, String l_strUpdaterCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateGFTTransferStatus(GftTransferStatusParams l_gftTransferStatusParams, " +
            "String l_strUpdatedTransferStatusDiv, String l_strUpdaterCode)";
        log.entering(STR_METHOD_NAME);
        
        if(l_gftTransferStatusParams == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�jGFT�U�֏�Params�C���X�^���X���쐬����B
        //�Q�j���������C���X�^���X�ɁA����.GFT�U�֏󋵂̃v���p�e�B���R�s�[����B
        GftTransferStatusParams l_gftTransferStatusParamsForUpdate = 
            new GftTransferStatusParams(l_gftTransferStatusParams);
        
        //�R�j���������C���X�^���X�ɁA�X�V��̃f�[�^���Z�b�g����B 
        //�@@�X�V����s�̓��e�͉��L���Q�ƁB 
        //�@@�y��Trade�z�⑫����.DB�X�V 
        //�@@�uFX�U�֋���_GFT�U�֏󋵃e�[�u��.xls�v�� 
        //�@@�uGFT�U�֏󋵃e�[�u��_DB�X�V�d�l[�ۏ؋��U�֊Ǘ�]�v�V�[�g
        try
        {
            //3.1) �U�֏󋵋敪
            //���� : ����.�X�V��U�֏󋵋敪
            l_gftTransferStatusParamsForUpdate.setTransferStatusDiv(l_strUpdatedTransferStatusDiv);
            
            //3.2) �X�V�҃R�[�h�j
            //���� : ����.�X�V�҃R�[�h
            l_gftTransferStatusParamsForUpdate.setLastUpdater(l_strUpdaterCode);
            
            //3.3) �X�V���t
            //���� : ���ݎ���
            l_gftTransferStatusParamsForUpdate.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            //�S�jGFT�U�֏󋵂�update 
            //WEB3DataAccessUtility.updateRow()���\�b�h���R�[������B 
            //�@@[updateRow()�ɃZ�b�g����p�����[�^] 
            //�@@�@@l_row�F�@@�R�j�ɂčX�V��̃f�[�^���Z�b�g�����C���X�^���X            
            WEB3DataAccessUtility.updateRow(l_gftTransferStatusParamsForUpdate);
        
        }
        catch (DataException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (updateGFT�U�֏�) <BR>
     * SOAP��M���ʂ�GFT�U�֏󋵃e�[�u���̃f�[�^�ɔ��f����B <BR>
     *  <BR>
     * �P�jGFT�U�֏�Params�C���X�^���X���擾����B <BR>
     *  <BR>
     *    this.getGFT�U�֏�()���R�[������B <BR>
     *  <BR>
     *    [����] <BR>
     *    �،���ЃR�[�h�F ����.�،���ЃR�[�h <BR>
     *    ���X�R�[�h�F ����.���X�R�[�h <BR>
     *    ���ʃR�[�h�F ����.���ʃR�[�h <BR>
     *  <BR>
     * �Q�j�擾����GFT�U�֏�Params��clone�𐶐�����B <BR>
     *  <BR>
     * �R�jclone�ɁA��t���ʃR�[�h���Z�b�g����B <BR>
     *  <BR>
     * �@@�X�V����s�̓��e�͉��L���Q�ƁB <BR>
     *  <BR>
     * �@@�y��Trade�z�⑫����.DB�X�V  <BR>
     * �@@�uFX�U�֋���_GFT�U�֏󋵃e�[�u��.xls�v�� <BR>
     * �@@�uGFT�U�֏󋵃e�[�u��_DB�X�V�d�l[SOAP�ڑ����ʍX�V]�v�V�[�g <BR>
     *  <BR>
     * �S�jGFT�U�֏󋵂�update <BR>
     *  WEB3DataAccessUtility.updateRow()���\�b�h���R�[������B <BR>
     *  <BR>
     * �@@[updateRow()�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@l_row�F�@@�R�j�ɂĎ�t���ʃR�[�h���Z�b�g�����C���X�^���X <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strRequestCode - (���ʃR�[�h)<BR>
     * ���ʃR�[�h<BR>
     * @@param l_strResultCode - (��t���ʃR�[�h)<BR>
     * ��t���ʃR�[�h<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41CBC3DD00B3
     */
    public void updateGFTTransferStatus(
        String l_strInstitutionCode, String l_strBranchCode,
        String l_strRequestCode, String l_strResultCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateGFTTransferStatus(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�jGFT�U�֏�Params�C���X�^���X���擾����B
        //this.getGFT�U�֏�()���R�[������B
        //[����]
        //�،���ЃR�[�h�F ����.�،���ЃR�[�h
        //���X�R�[�h�F ����.���X�R�[�h
        //���ʃR�[�h�F ����.���ʃR�[�h
        GftTransferStatusParams l_gftTransferStatusParams =
            this.getGFTTransferStatus(l_strInstitutionCode, l_strBranchCode, l_strRequestCode);

        if (l_gftTransferStatusParams == null)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���B");
        }
    
        //�Q�j�擾����GFT�U�֏�Params��clone�𐶐�����B
        GftTransferStatusParams l_gftTransferStatusParamsClone = new GftTransferStatusParams(l_gftTransferStatusParams);

        //�X�V����s�̓��e�͉��L���Q�ƁB
        //�y��Trade�z�⑫����.DB�X�V 
        //�uFX�U�֋���_GFT�U�֏󋵃e�[�u��.xls�v��
        //�uGFT�U�֏󋵃e�[�u��_DB�X�V�d�l[SOAP�ڑ����ʍX�V]�v�V�[�g
        try
        {
            //�R�jclone�ɁA��t���ʃR�[�h���Z�b�g����B
            l_gftTransferStatusParamsClone.setResultCodeSoap(l_strResultCode);

            //3.3) �X�V���t
            //���� : ���ݎ���
            l_gftTransferStatusParamsClone.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            //�S�jGFT�U�֏󋵂�update
            //WEB3DataAccessUtility.updateRow()���\�b�h���R�[������B
            //[updateRow()�ɃZ�b�g����p�����[�^]
            //l_row�F�@@�R�j�ɂĎ�t���ʃR�[�h���Z�b�g�����C���X�^���X            
            WEB3DataAccessUtility.updateRow(l_gftTransferStatusParamsClone);
        }
        catch (DataQueryException l_dqex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_dqex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqex.getMessage(),
                l_dqex);
        }
        catch (DataNetworkException l_dnex)
        {
            log.error("DB�A�N�Z�X�G���[", l_dnex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dnex.getMessage(),
                l_dnex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (updateFX�����J�݋敪) <BR>
     * �ڋq�}�X�^�[�e�[�u����FX�����J�݋敪��update����B <BR>
     * <BR>
     * �P�j�ڋq�̎擾 <BR>
     * �g���A�J�E���g�}�l�[�W��.get�ڋq()�ɂČڋq�I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * [�����̐ݒ�] <BR>
     * �،���ЃR�[�h�F ����.�،���ЃR�[�h <BR>
     * ���X�R�[�h�F ����.���X�R�[�h <BR>
     * �����R�[�h�F ����.�ڋq�R�[�h <BR>
     * <BR>
     * �擾�����ڋq.getDataSourceObject()�ɂ��ڋqParams���擾����B <BR>
     * <BR>
     * �R�j���������C���X�^���X�ɁA�X�V��̃f�[�^���Z�b�g����B <BR>
     * <BR>
     * �X�V����s�̓��e�͉��L���Q�ƁB <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �uFX�����J�݋���_�ڋq�}�X�^�[.xls�v�Q�� <BR>
     * <BR>
     * �S�j�ڋq��update <BR>
     * WEB3DataAccessUtility.updateRow()���\�b�h���R�[������B <BR>
     * <BR>
     * [updateRow()�ɃZ�b�g����p�����[�^] <BR>
     * l_row�F �R�j�ɂčX�V��̃f�[�^���Z�b�g�����C���X�^���X <BR>
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@param l_strUpdatedFxAccOpenDiv - �X�V��FX�����J�݋敪
     *          0�FDEFAULT(�����Ȃ�) 1�F�����J��
     * @@param l_strUpdaterCode - �X�V�҃R�[�h
     * @@throws WEB3BaseException
     * @@roseuid 41CBF15D02DF
     */
    public void updateFXAccountOpenDiv(String l_strInstitutionCode,
        String l_strBranchCode, String l_strAccountCode,
        String l_strUpdatedFxAccOpenDiv, String l_strUpdaterCode)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateFXAccountOpenDiv(String l_strInstitutionCode," +
            "String l_strBranchCode, String l_strAccountCode, " +
            "String l_strUpdatedFxAccOpenDiv, String l_strUpdaterCode)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //�P�j�ڋq�̎擾 
            //�@@�g���A�J�E���g�}�l�[�W��.get�ڋq()�ɂČڋq�I�u�W�F�N�g���擾����B 
            //�@@[�����̐ݒ�] 
            //�@@�،���ЃR�[�h�F�@@����.�،���ЃR�[�h 
            //�@@���X�R�[�h�F�@@����.���X�R�[�h 
            //�@@�����R�[�h�F�@@����.�ڋq�R�[�h 
            //�@@�擾�����ڋq.getDataSourceObject()�ɂ��ڋqParams���擾����B
            WEB3GentradeAccountManager l_web3GentradeAccountManager =
                (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
            
            MainAccount l_mainAccount = 
                l_web3GentradeAccountManager.getMainAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
            
            MainAccountRow l_mainAccountRow = 
                (MainAccountRow)l_mainAccount.getDataSourceObject();
            
            //�R�j���������C���X�^���X�ɁA�X�V��̃f�[�^���Z�b�g����B 
            //�@@�X�V����s�̓��e�͉��L���Q�ƁB 
            //�@@�y��Trade�z�⑫����.DB�X�V 
            //�@@�uFX�����J�݋���_�ڋq�}�X�^�[.xls�v�Q��
            MainAccountParams l_mainAccountParams = new MainAccountParams(l_mainAccountRow);
            
            //3.1) �e�w�����J�݋敪
            //���� : ����.�X�V��FX�����J�݋敪
            l_mainAccountParams.setFxAccOpenDiv(l_strUpdatedFxAccOpenDiv);
            
            //3.2) �e�w�����J�݋敪�X�V�҃R�[�h
            //���� : ����.�X�V�҃R�[�h
            l_mainAccountParams.setFxAccOpenDivLastUpdater(l_strUpdaterCode);

            //3.3) �e�w�����J�݋敪�X�V����
            //���� : ���ݎ���
            l_mainAccountParams.setFxAccOpenUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //3.4) �X�V����
            l_mainAccountParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //�S�j�ڋq��update 
            //WEB3DataAccessUtility.updateRow()���\�b�h���R�[������B 
            //�@@[updateRow()�ɃZ�b�g����p�����[�^] 
            //�@@�@@l_row�F�@@�R�j�ɂčX�V��̃f�[�^���Z�b�g�����C���X�^���X
            WEB3DataAccessUtility.updateRow(l_mainAccountParams);
        }
        catch (DataException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createFX�������ꗗ) <BR>
     * �����̏����ɊY������FX�������̈ꗗ���쐬����B <BR>
     * <BR>
     * �P�jthis.getFX�����ԍ�()���\�b�h���R�[������B <BR>
     * <BR>
     * [getFX�����ԍ�()�ɃZ�b�g����p�����[�^] <BR>
     * �،���ЃR�[�h�F ����.�،���ЃR�[�h <BR>
     * ���X�R�[�h�F ����.���X�R�[�h <BR>
     * �ڋq�R�[�h�F ����.�ڋq�R�[�h <BR>
     * <BR>
     * null���ԋp���ꂽ�ꍇ�Anull��ԋp����B <BR>
     * <BR>
     * �Q�jArrayList�𐶐�����B <BR>
     * <BR>
     * �R�j�P�j�̖߂�l�̗v�f�����A�ȉ��̏������J��Ԃ��B <BR>
     * �R�|�P�jFX�������C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �R�|�Q�j���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B <BR>
     * �R�[�X�敪 = FX�����ԍ�Params.FX�R�[�X�敪 <BR>
     * �����ԍ� = FX�����ԍ�Params.FX�����ԍ� <BR>
     * <BR>
     * �R�|�R�jArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B <BR>
     * <BR>
     * �S�j��������ArrayList.toArray()�̖߂�l��ԋp����B <BR>
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@return webbroker3.aio.message.WEB3FXAccInformationUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 41C7EF4C0152
     */
    public WEB3FXAccInformationUnit[] createFXAccInformationUnits(
        String l_strInstitutionCode, String l_strBranchCode,
        String l_strAccountCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "createFXAccInformationUnits(String l_strInstitutionCode, " +
            " String l_strBranchCode,String l_strAccountCode)";
        log.entering(STR_METHOD_NAME);

        //�P�jthis.getFX�����ԍ�()���\�b�h���R�[������B 
        //�@@[getFX�����ԍ�()�ɃZ�b�g����p�����[�^] 
        //�@@�@@�،���ЃR�[�h�F�@@����.�،���ЃR�[�h 
        //�@@�@@���X�R�[�h�F�@@����.���X�R�[�h 
        //�@@�@@�ڋq�R�[�h�F�@@����.�ڋq�R�[�h 
        //�@@null���ԋp���ꂽ�ꍇ�Anull��ԋp����B 
        FxAccountCodeParams[] l_fxAccountCodeParams = 
            this.getFXAccountCodes(l_strInstitutionCode, l_strBranchCode, l_strAccountCode);
        
        if(l_fxAccountCodeParams == null)
        {
            return null;
        }

        //�Q�jArrayList�𐶐�����B
        List l_lisFXAccInformationUnit = new Vector();
        
        for(int i = 0; i < l_fxAccountCodeParams.length; i++)
        {
            //�R�j�P�j�̖߂�l�̗v�f�����A�ȉ��̏������J��Ԃ��B 
            //�@@�R�|�P�jFX�������C���X�^���X�𐶐�����B 
            WEB3FXAccInformationUnit l_updatedFxAccInformation = new WEB3FXAccInformationUnit();
            
            //�@@�R�|�Q�j���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B 
            //�@@�@@�R�[�X�敪 = FX�����ԍ�Params.FX�R�[�X�敪
            l_updatedFxAccInformation.fxCourseDiv = l_fxAccountCodeParams[i].getFxCourseDiv();
            
            //�@@�@@�����ԍ� = FX�����ԍ�Params.FX�����ԍ� 
            l_updatedFxAccInformation.fxAccountCode = l_fxAccountCodeParams[i].getFxAccountCode();
            
            //�@@�R�|�R�jArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B 
            l_lisFXAccInformationUnit.add(l_updatedFxAccInformation);
        }

        //�S�j��������ArrayList.toArray()�̖߂�l��ԋp����B
        WEB3FXAccInformationUnit[] l_fXAccInformationUnit = 
            new WEB3FXAccInformationUnit[l_fxAccountCodeParams.length];
        
        l_lisFXAccInformationUnit.toArray(l_fXAccInformationUnit);
        
        log.exiting(STR_METHOD_NAME);
        return l_fXAccInformationUnit;
            
    }

    /**
     * (get�V�KFX���O�C��ID) <BR>
     * FX���O�C��ID��t�Ԃ��ĕԋp����B<BR> 
     * <BR>
     * FX���O�C��ID�̕t�ԃ��[����<BR> 
     * FX���O�C��ID������ + �ڋq�R�[�h�Ƃ���B<BR> 
     * <BR>
     * ����.FX���O�C��ID������ + ����.�ڋq�R�[�h(*)�𕶎���A�������l��ԋp����B<BR> 
     * <BR>
     * (*)�ڋq�R�[�h�̐擪����6���ڂ܂ł��g�p����<BR> 
     * @@param l_strFXloginFirstChar - FX���O�C��ID������
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@return String
     * @@roseuid 41C964AA0206
     */
    public String getNewFXLoginID(String l_strFXloginFirstChar,
        String l_strAccountCode)
    {
        String STR_METHOD_NAME =
            "getNewFXLoginID(String l_strFXloginFirstChar,String l_strAccountCode)";
        log.entering(STR_METHOD_NAME);
        
        if(l_strAccountCode == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //FX���O�C��ID�̕t�ԃ��[���� 
        //FX���O�C��ID������ + �ڋq�R�[�h�Ƃ���B 
        //����.FX���O�C��ID������ + ����.�ڋq�R�[�h(*)�𕶎���A�������l��ԋp����B 
        //(*)�ڋq�R�[�h�̐擪����6���ڂ܂ł��g�p���� 
        String l_strAccountCodeNew = l_strAccountCode;
        if(l_strAccountCode.length() > 6)
        {
            l_strAccountCodeNew = l_strAccountCode.substring(0, 6);
        }
        
        String l_strFxLoginId = l_strFXloginFirstChar + l_strAccountCodeNew;
        
        log.exiting(STR_METHOD_NAME);
        return l_strFxLoginId;
    }

    /**
     * (get�V�KFX�ڋqID) <BR>
     * FX�ڋqID��t�Ԃ��ĕԋp����B <BR>
     * <BR>
     * FX�ڋqID�̕t�ԃ��[���� <BR>
     * �،����ID + ���X�R�[�h + FX�V�X�e���R�[�h + �ڋq�R�[�h(*1)�Ƃ���B <BR>
     * <BR>
     * �،����ID(*2) + ����.���X�R�[�h + ����.FX�V�X�e���R�[�h<BR>
     * �@@+ ����.�ڋq�R�[�h�𕶎���A�������l��ԋp����B<BR>
     * <BR>
     * (*1)<BR>
     * ����.�ڋq�R�[�h == 7���̏ꍇ�A <BR>
     * ����.�ڋq�R�[�h�̐擪����6���ڂ܂ł��g�p���� <BR>
     * <BR>
     * (*2)<BR>
     * �g���A�J�E���g�}�l�[�W��.getInstitution().�،����ID <BR>
     * <BR>
     * [getInstitution()�ɃZ�b�g����p�����[�^] <BR>
     * �،���ЃR�[�h�F ����.�،���ЃR�[�h <BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@param l_strFxSystemCode - FX�V�X�e���R�[�h
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 41C9730F01B7
     */
    public String getNewFXAccountID(String l_strInstitutionCode,
        String l_strBranchCode, String l_strAccountCode, String l_strFxSystemCode)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "getNewFXAccountID(String l_strInstitutionCode, " +
            "String l_strBranchCode, String l_strAccountCode, String l_strFxSystemCode)";
        log.entering(STR_METHOD_NAME);
        
        if(l_strAccountCode == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //FX�ڋqID�̕t�ԃ��[���� 
        //�،����ID + ���X�R�[�h + FX�V�X�e���R�[�h + �ڋq�R�[�h(*1)�Ƃ���B 
        //�،����ID(*2) + ����.���X�R�[�h + ����.FX�V�X�e���R�[�h + ����.�ڋq�R�[�h�𕶎���A�������l��ԋp����
        //(*1)
        //����.�ڋq�R�[�h == 7���̏ꍇ�A
        //����.�ڋq�R�[�h�̐擪����6���ڂ܂ł��g�p���� 
        String l_strAccountCodeNew = l_strAccountCode;
        if(l_strAccountCode.length() == 7)
        {
            l_strAccountCodeNew = l_strAccountCode.substring(0, 6);
        }
        
        long l_lngInstitutionId = 0;
        try
        {
            //(*2)
            //�g���A�J�E���g�}�l�[�W��.getInstitution().�،����ID 
            //[getInstitution()�ɃZ�b�g����p�����[�^] 
            //�،���ЃR�[�h�F�@@����.�،���ЃR�[�h
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            Institution l_institution = l_accountManager.getInstitution(
                l_strInstitutionCode);

            l_lngInstitutionId = l_institution.getInstitutionId();
        }
        catch(NotFoundException l_ex)
        {
            log.debug("__NotFoundException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex); 
        }

        String l_strFxAccountId = Long.toString(l_lngInstitutionId) + l_strBranchCode 
            + l_strFxSystemCode + l_strAccountCodeNew;
        
        log.exiting(STR_METHOD_NAME);
        return l_strFxAccountId;
    }

    /**
     * (validateFX������ӎ���) <BR>
     * FX������ӎ���ɑ΂���񓚂̐��������`�F�b�N����B <BR>
     * <BR>
     * ����.FX������ӎ�����ꗗ�̗v�f���Ƃ�Loop�����ɂāA <BR>
     * �ȉ��̃`�F�b�N���s���B <BR>
     * <BR>
     * FX������ӎ�����.����񓚁��h1�F���Ӂh�̏ꍇ�A��O��thorw����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01796 <BR>
     * <BR>
     * @@param l_fxTradeAgreementList - FX������ӎ�����̈ꗗ
     * @@throws WEB3BaseException
     * @@roseuid 41D0F49300CF
     */
    public void validateFXTradingAgreeQuestion(
        WEB3FXTradeAgreementUnit[] l_fxTradeAgreementList) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "validateFXTradingAgreeQuestion(WEB3FXTradeAgreementUnit[] l_fxTradeAgreementList)";
        log.entering(STR_METHOD_NAME);
        
        if(l_fxTradeAgreementList == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //����.FX������ӎ�����ꗗ�̗v�f���Ƃ�Loop�����ɂāA 
        //�ȉ��̃`�F�b�N���s���B
        //FX������ӎ�����.����񓚁��h1�F���Ӂh�̏ꍇ�A��O��thorw����B
        for(int i = 0; i < l_fxTradeAgreementList.length; i++)
        {
            log.debug("l_fxTradeAgreementList[" + i + "].questionAnswer = " + l_fxTradeAgreementList[i].questionAnswer);
            if(!WEB3AioQuestionAnswerDef.AGREE.equals(l_fxTradeAgreementList[i].questionAnswer))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01796,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "FX������ӎ�����.����񓚁��h1�F���Ӂh");
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit�������) <BR>
     * �U�֒����̎�������Ɨ]�͂̍X�V�������s���B <BR>
     * <BR>
     * �P�j�����̃��b�N�����s���B <BR>
     * <BR>
     * �g���A�J�E���g�}�l�[�W��.lock����()���R�[������B <BR>
     * <BR>
     * [����] <BR>
     * �،���ЃR�[�h�F ����.�،���ЃR�[�h <BR>
     * ���X�R�[�h�F ����.���X�R�[�h <BR>
     * �����R�[�h�F ����.�ڋq�R�[�h <BR>
     * <BR>
     * �Q�j�U�֒����̎���������s���B <BR>
     * <BR>
     * AIO�����}�l�[�W��.�U�֒������()���R�[������B <BR>
     * <BR>
     * [����] <BR>
     * �،���ЃR�[�h�F ����.�،���ЃR�[�h <BR>
     * ���X�R�[�h�F ����.���X�R�[�h <BR>
     * �ڋq�R�[�h�F ����.�ڋq�R�[�h <BR>
     * ���ʃR�[�h�F ����.���ʃR�[�h <BR>
     * �M�p�U�֗p���ʃR�[�h�F ����.�M�p�U�֗p���ʃR�[�h <BR>
     * <BR>
     * �R�j�⏕�����I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �R�|�P�j <BR>
     * �g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[������B <BR>
     * <BR>
     * [����] <BR>
     * �،���ЃR�[�h�F ����.�،���ЃR�[�h <BR>
     * ���X�R�[�h�F ����.���X�R�[�h <BR>
     * �����R�[�h�F ����.�ڋq�R�[�h <BR>
     * <BR>
     * �R�|�Q�j <BR>
     * �ڋq.getSubAccount()���R�[������B <BR>
     * <BR>
     * [����] <BR>
     * �⏕�����^�C�v�F �h�a��������h <BR>
     * <BR>
     * �S�j�]�͂̍X�V���s���B <BR>
     * <BR>
     * �]�͎���T�[�r�XImpl.�]�͍Čv�Z()���R�[������B <BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@param l_strMrgTrnRequestNumber - �M�p�U�֗p���ʃR�[�h
     * @@throws WEB3BaseException
     * @@roseuid 41D0F49300CF
     */
    public void submitCancelOrder(
        String l_strInstitutionCode, String l_strBranchCode, 
        String l_strAccountCode, String l_strRequestNumber, 
        String l_strMrgTrnRequestNumber) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "submitCancelOrder(" +
            "String l_strInstitutionCode, String l_strBranchCode, " + 
            "String l_strAccountCode, String l_strRequestNumber, " +
            "String l_strMrgTrnRequestNumber)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //�P�j�����̃��b�N�����s���B
        //�g���A�J�E���g�}�l�[�W��.lock����()���R�[������B
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        try
        {
            l_accountManager.lockAccount(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__error in lockAccount__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j�U�֒����̎���������s���B
        //���o�������}�l�[�W���N���X���擾����B
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        
        //AIO�����}�l�[�W��.�U�֒������()���R�[������B
        l_aioOrderManager.transferOrderCancel(l_strInstitutionCode, 
                l_strBranchCode, l_strAccountCode, 
                l_strRequestNumber, l_strMrgTrnRequestNumber);
        
        //�R�j�⏕�����I�u�W�F�N�g���擾����B 
        //�R�|�P�j 
        //�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[������B 
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        MainAccount l_mainAccount = null; 
        
        //�⏕�����I�u�W�F�N�g���擾����B 
        l_mainAccount = l_accMgr.getMainAccount(
            l_strInstitutionCode, l_strBranchCode, l_strAccountCode);
        
        //�R�|�Q�j 
        //�ڋq.getSubAccount()���R�[������B 
        //[����] 
        //�⏕�����^�C�v�F �h�a��������h 

        //�⏕����
        SubAccount l_subAccount = null;
        try
        {
            //�⏕�����I�u�W�F�N�g���擾����B 
            l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�⏕�����I�u�W�F�N�g���擾", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //�S�j�]�͂̍X�V���s���B 
        //�]�͎���T�[�r�XImpl.�]�͍Čv�Z()���R�[������B 
        //[����] 
        //�⏕�����F �Q�|�Q�j�Ŏ擾�����⏕�����I�u�W�F�N�g 
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);

        WEB3GentradeSubAccount l_gentradeSubAccount =
            (WEB3GentradeSubAccount)l_subAccount;

        l_service.reCalcTradingPower(l_gentradeSubAccount);        
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (insert�����s�v��������) <BR>
     * �����J�ݐ\�����Ɂu�����s�v�v��I�������ꍇ�A<BR> 
     * FX�����s�v���������Ǘ��e�[�u��.�ɍs��insert���s���B<BR> 
     * <BR>
     * �P�j�،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h���L�[�Ƃ���<BR> 
     * �@@ FX�����s�v���������Ǘ��e�[�u����藚��ԍ��̍ő�l���擾����B<BR> 
     * <BR>
     * �Q�j�擾��������ԍ��ɂP�v���X�����l��<BR> 
     * �@@ FX�����s�v���������Ǘ�.����ԍ��ɃZ�b�g����B<BR> 
     * �@@ �P�j�ɂĎ擾�ł��Ȃ��ꍇ�́A�P���Z�b�g����B <BR>
     * <BR>
     * ����ȊO�̑}������s�̓��e�Ɋւ��ẮA���L���Q�ƁB<BR> 
     * �y��Trade�z�⑫����.DB�X�V  <BR>
     * �uFX���ʁ@@FX�����s�v���������Ǘ��e�[�u��.xls<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - ���X�R�[�h<BR>
     * @@param l_strAccountCode - �ڋq�R�[�h<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41D0F49D02B3
     */
    public void insertUnnecessaryExplanation(
        String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strAccountCode) 
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "insertUnnecessaryExplanation(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h���L�[�Ƃ��� 
        //FX�����s�v���������Ǘ��e�[�u����藚��ԍ��̍ő�l���擾����B
        try
        {
            log.debug("�ڋq�R�[�h = " + l_strAccountCode);
            
            String l_strWheres  = "institution_code = ? and branch_code = ? " +
                "and account_code = ? ";
            Object[] l_objBindVal = {
                l_strInstitutionCode, 
                l_strBranchCode, 
                l_strAccountCode, 
                };
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            List l_lisFxUnnecessaryExplanationRows = 
                l_processor.doFindAllQuery(
                    FxUnnecessaryExplanationRow.TYPE,
                    l_strWheres,
                    " fx_serial_no desc",
                    null,
                    l_objBindVal); 
                        
            //�Q�j�擾��������ԍ��ɂP�v���X�����l�� 
            //FX�����s�v���������Ǘ�.����ԍ��ɃZ�b�g����B 
            //�P�j�ɂĎ擾�ł��Ȃ��ꍇ�́A�P���Z�b�g����B             
            //����ȊO�̑}������s�̓��e�Ɋւ��ẮA���L���Q�ƁB 
            //�y��Trade�z�⑫����.DB�X�V  
            //�uFX���ʁ@@FX�����s�v���������Ǘ��e�[�u��.xls�v    
            
            //�ŐV�̗���ԍ�
            int l_intLastNumber = 1;
            if (l_lisFxUnnecessaryExplanationRows != null &&  
                !l_lisFxUnnecessaryExplanationRows.isEmpty())
            {
                FxUnnecessaryExplanationRow l_fxUnnecessaryExplanationRow = 
                    (FxUnnecessaryExplanationRow)l_lisFxUnnecessaryExplanationRows.get(0);
                
                l_intLastNumber = l_fxUnnecessaryExplanationRow.getFxSerialNo() + 1;
            }
            
            //FX�����s�v���������Ǘ��e�[�u��
            FxUnnecessaryExplanationParams l_fxUnnecessaryExplanationParams = 
                new FxUnnecessaryExplanationParams(); 
            //�،���ЃR�[�h: ����.�،���ЃR�[�h
            l_fxUnnecessaryExplanationParams.setInstitutionCode(l_strInstitutionCode);
            //���X�R�[�h: ����.���X�R�[�h
            l_fxUnnecessaryExplanationParams.setBranchCode(l_strBranchCode);
            //�ڋq�R�[�h: ����.�ڋq�R�[�h
            l_fxUnnecessaryExplanationParams.setAccountCode(l_strAccountCode);
            //����ԍ�: �،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h���L�[�Ɏ擾�����ŐV����ԍ��{�P
            l_fxUnnecessaryExplanationParams.setFxSerialNo(l_intLastNumber);
            //�L���t���O: 0�F�L��
            l_fxUnnecessaryExplanationParams.setFxValidFlag(WEB3EffectiveDivDef.EFFECTIVE);
            //�X�V�҃R�[�h: null
            l_fxUnnecessaryExplanationParams.setLastUpdater(null);
            //�쐬���t: �V�X�e���^�C���X�^���v
            l_fxUnnecessaryExplanationParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            //�X�V���t: �V�X�e���^�C���X�^���v
            l_fxUnnecessaryExplanationParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            l_processor.doInsertQuery(l_fxUnnecessaryExplanationParams);
        }
        catch (DataQueryException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
        log.exiting(STR_METHOD_NAME);
        return; 
    }
    
    /**
     * (validateFX�h�L�������g�{������) <BR>
     * �d�q���V�X�e���֐ڑ����A<BR>
     * �Y���ڋq�̉{�����������݂��邩�̃`�F�b�N���s���B<BR> 
     * <BR>
     * �P�j�ژ_�����{���`�F�b�N<BR> 
     * �@@�P-�P�jArrayList�̍쐬 <BR>
     * <BR>
     *  �P-�Q�j���ʃR�[�h�̗v�f�����A�P�|�R�j�`�P�|�T�j�̏��������{<BR> 
     * <BR>
     *  �P-�R�j�d�q���V�X�e���ڑ��T�[�r�XImpl.validate�ژ_�����{�������{<BR> 
     *   [����] <BR>
     *    ��ʃR�[�h�F����.��ʃR�[�h<BR> 
     *    ���ʃR�[�h�F����.���ʃR�[�h[index]<BR> 
     * <BR>
     *  �P-�S�j�擾�����ژ_�����{���`�F�b�N����.�`�F�b�N���ʂ��u1�F �{�����ρv��<BR> 
     *   �ꍇ�AArrayList�Ɏ��ʃR�[�h��ǉ��B <BR>
     * <BR>
     * �Q�j�ژ_�����{���`�F�b�N���ʂ̊m�F<BR> 
     * �@@�Q-�P�j�쐬����ArrayList�i�P-�P�ō쐬�j�̗v�f�����u�O�v�̏ꍇ�A�uNULL�v��<BR> 
     *   �ԋp����B <BR>
     * <BR>
     *   �Q-�Q�j�쐬����ArrayList�i�P-�P�ō쐬�j�̗v�f�����u�O�v�łȂ��ꍇ�A<BR> 
     *   �z��ɕϊ��������ʃR�[�h��ԋp����B <BR>
     * 
     * @@param l_strTypeCode - ��ʃR�[�h
     * @@param l_strRequestCode - ���ʃR�[�h
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 41D0F49D02B3
     */
    public String[] validateFxDocReadHistory(
        String l_strTypeCode, 
        String[] l_strRequestCodes) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateFxDocReadHistroy(String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strRequestCodes == null)
        {
            log.debug("���ʃR�[�h = " + l_strRequestCodes);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�ژ_�����{���`�F�b�N 
        //�P-�P�jArrayList�̍쐬 
        List l_lisRequestCodes = new ArrayList();
        
        // �P-�Q�j���ʃR�[�h�̗v�f�����A�P�|�R�j�`�P�|�S�j�̏��������{
        for (int i = 0; i < l_strRequestCodes.length; i++)
        {
            //�P-�R�j�d�q���V�X�e���ڑ��T�[�r�XImpl.validate�ژ_�����{�������{ 
            // [����] 
            //  ��ʃR�[�h�F����.��ʃR�[�h 
            //  ���ʃR�[�h�F����.���ʃR�[�h[index] 
            WEB3GentradeBatoClientService l_gentradeBatoClientService = 
                (WEB3GentradeBatoClientService)Services.getService(WEB3GentradeBatoClientService.class);
            
            WEB3GentradeProspectusResult l_prospectusResult = 
                l_gentradeBatoClientService.validateProspectus(
                    l_strTypeCode,
                    l_strRequestCodes[i]);
            
            // �P-�S�j�擾�����ژ_�����{���`�F�b�N����.�`�F�b�N���ʂ��u1�F �{�����ρv�� 
            //�ꍇ�AArrayList�Ɏ��ʃR�[�h��ǉ��B
            if (WEB3GentradeBatoCheckResultDef.UNINSPECTION.equals(l_prospectusResult.checkResult))
            {
                l_lisRequestCodes.add(l_strRequestCodes[i]);
            }            
        }
        //�Q�j�ژ_�����{���`�F�b�N���ʂ̊m�F 
        //�@@�Q-�P�j�쐬����ArrayList�i�P-�P�ō쐬�j�̗v�f�����u�O�v�̏ꍇ�A�uNULL�v�� 
        //�ԋp����B 
        if (l_lisRequestCodes.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            String[] l_strResult = new String[l_lisRequestCodes.size()];
            l_lisRequestCodes.toArray(l_strResult);
            return l_strResult;
        }        
    }
    
    /**
     * (get��Е�FX�V�X�e������)<BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�AFX�V�X�e���R�[�h�Ɉ�v����<BR>
     * ��Е�FX�V�X�e������Params��ԋp����B<BR> 
     * <BR>
     * �P�j�����`�F�b�N <BR>
     * �@@�����DFX�V�X�e���R�[�h��null���ǂ����`�F�b�N���� <BR>
     * <BR>
     * �Q�|�P�jDB����<BR> 
     * �@@�����DFX�V�X�e���R�[�h��null�łȂ��ꍇ�A<BR>
     * �@@��Е�FX�V�X�e�������e�[�u��(comp_fx_condition)���ȉ��̏����Ō�������B<BR> 
     * �@@�������A�������擾�̏ꍇ�͗�O���X���[����B <BR>
     * <BR>
     * class: WEB3SystemLayerException <BR>
     * tag: SYSTEM_ERROR_80006 <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�،���ЃR�[�h = ����.�،���ЃR�[�h <BR>
     * �@@���X�R�[�h = ����.���X�R�[�h <BR>
     *   FX�V�X�e���R�[�h = ����.FX�V�X�e���R�[�h<BR> 
     * <BR>
     * �Q�|�Q�jDB���� <BR>
     * �@@�����DFX�V�X�e���R�[�h��null�̏ꍇ�A<BR>
     * �@@��Е�FX�V�X�e�������e�[�u��(comp_fx_condition)���ȉ��̏����Ō�������B<BR> 
     * �@@�������A�������擾�̏ꍇ�͗�O���X���[����B <BR>
     * <BR>
     * class: WEB3SystemLayerException <BR>
     * tag: SYSTEM_ERROR_80006 <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR> 
     * �@@���X�R�[�h = ����.���X�R�[�h <BR>
     * <BR>
     * �R�j�������ʂ̉�Е�FX�V�X�e������Params��ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strBranchCode - (���X�R�[�h)
     * @@param l_strFxSystemCode - (FX�V�X�e���R�[�h)
     * @@return webbroker3.aio.data.CompFxConditionParams
     * @@throws WEB3BaseException 
     * @@throws NotFoundException 
     */
    public CompFxConditionParams getCompFxCondition(
        String l_strInstitutionCode, String l_strBranchCode, String l_strFxSystemCode) 
            throws WEB3BaseException, NotFoundException
    {
        String STR_METHOD_NAME = "getCompFxCondition( " +
        "String l_strInstitutionCode, String l_strBranchCode, String l_strFxSystemCode)";
        log.entering(STR_METHOD_NAME);
        
        //�Q�|�P�jDB���� 
        //�@@�����DFX�V�X�e���R�[�h��null�łȂ��ꍇ�A��Е�FX�V�X�e�������e�[�u��(comp_fx_condition)���ȉ��̏����Ō�������B 
        //�@@�������A�������擾�̏ꍇ�͗�O���X���[����B 
        //
        //�@@[����] 
        //�@@�،���ЃR�[�h = ����.�،���ЃR�[�h 
        //�@@���X�R�[�h = ����.���X�R�[�h 
        //  FX�V�X�e���R�[�h = ����.FX�V�X�e���R�[�h 
        List l_lisRows = null;
        if (l_strFxSystemCode != null) 
        {
            String l_strWhere = "institution_code = ? and branch_code = ? and fx_system_code = ?";        
            Object[] l_objValue = {l_strInstitutionCode, l_strBranchCode, l_strFxSystemCode};
            try 
            {
                l_lisRows =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        CompFxConditionRow.TYPE,
                        l_strWhere,
                        null,
                        l_objValue);
            } 
            catch (DataException l_ex) 
            {
                log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            if (l_lisRows.size() == 0)
            {
                throw new NotFoundException("��Е�FX�V�X�e�������e�[�u�����擾�ł��܂���ł���");
            }
            
            //�������擾�̏ꍇ�͗�O���X���[����B
            if (l_lisRows.size() > 1)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "��Е�FX�V�X�e�������e�[�u���ɕ�����");
            }
        }        
        //�Q�|�Q�jDB���� 
        //�@@�����DFX�V�X�e���R�[�h��null�̏ꍇ�A��Е�FX�V�X�e�������e�[�u��(comp_fx_condition)���ȉ��̏����Ō�������B 
        //�@@�������A�������擾�̏ꍇ�͗�O���X���[����B 
        //
        //�@@[����] 
        //�@@�،���ЃR�[�h = ����.�،���ЃR�[�h 
        //�@@���X�R�[�h = ����.���X�R�[�h 
        //
        //�R�j�������ʂ̉�Е�FX�V�X�e������Params��ԋp����B
        else 
        {
            String l_strWhere = "institution_code = ? and branch_code = ? ";        
            Object[] l_objValue = {l_strInstitutionCode, l_strBranchCode};
            try 
            {
                l_lisRows =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        CompFxConditionRow.TYPE,
                        l_strWhere,
                        null,
                        l_objValue);
            } 
            catch (DataException l_ex) 
            {
                log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            if (l_lisRows.size() == 0)
            {
                throw new NotFoundException("��Е�FX�V�X�e�������e�[�u�����擾�ł��܂���ł���");
            }
            
            //�������擾�̏ꍇ�͗�O���X���[����B
            if (l_lisRows.size() > 1)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "��Е�FX�V�X�e�������e�[�u���ɕ�����");
            }
        } 
        
        //�R�j�������ʂ̉�Е�FX�V�X�e������Params��ԋp����B
        CompFxConditionParams l_compFxConditionParams = 
            new CompFxConditionParams((CompFxConditionRow)l_lisRows.get(0));
        
        log.exiting(STR_METHOD_NAME);
        return l_compFxConditionParams;
    }

    /**
     * (getFX�U�֏����}�X�^)<BR>
     * FX�U�֏����}�X�^Params�擾���A�ԋp����B<BR>
     * <BR>
     * �P�jDB����<BR>
     * �@@�ȉ��̏����ŁAFX�U�֏����}�X�^(fx_transfer_master)����������B<BR>
     * �@@�y�����z<BR>
     * �@@FX�V�X�e������ID�@@= ����.FX�V�X�e������ID<BR>
     * �@@�U�֋敪 = ����.�U�֋敪<BR>
     * <BR>
     * �������ʂ�0���̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �Q�j�������ʂ�ԋp����B<BR>
     * <BR>
     * @@param l_lngFxSystemId - (FX�V�X�e������ID)<BR>
     * FX�V�X�e������ID<BR>
     * @@param l_strTransferDiv - (�U�֋敪)<BR>
     * �U�֋敪<BR>
     * @@return FxTransferMasterParams
     * @@throws WEB3BaseException
     */
    public FxTransferMasterParams getFxTransferMasterParams(
        long l_lngFxSystemId,
        String l_strTransferDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getFxTransferMasterParams(long, String)";
        log.entering(STR_METHOD_NAME);

        //DB����
        //�ȉ��̏����ŁAFX�U�֏����}�X�^(fx_transfer_master) ����������B
        //�y�����z
        //�@@FX�V�X�e������ID�@@= ����.FX�V�X�e������ID
        //�@@�U�֋敪 = ����.�U�֋敪
        FxTransferMasterRow l_fxTransferMasterRow = null;
        try
        {
            l_fxTransferMasterRow = FxTransferMasterDao.findRowByPk(
                l_lngFxSystemId,
                l_strTransferDiv);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
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

        //�������ʂ�ԋp����
        FxTransferMasterParams l_fxTransferMasterParams =
            new FxTransferMasterParams(l_fxTransferMasterRow);
        log.exiting(STR_METHOD_NAME);
        return l_fxTransferMasterParams;
    }

    /**
     * (get��n��)<BR>
     * �ȉ��̏����ɂĎ�n�����擾���A�ԋp����B<BR>
     * <BR>
     * �@@�P�jFX�U�֏����}�X�^.��n���ݒ�敪�������̏ꍇ<BR>
     * �@@�@@�u����.�������v�Ɂu����.��n���ݒ�敪�v�̒l�𑫂����c�Ɠ���ԋp����B<BR>
     * <BR>
     * �@@�Q�jFX�U�֏����}�X�^.��n���ݒ�敪�������ȊO�̏ꍇ<BR>
     * �@@�@@�Q�|�P�j��n���ݒ�敪���`�̏ꍇ<BR>
     * �@@�@@�@@�|����.�⏕����.getMainAccount.MRF�����J�݋敪 = �h0�FDEFAULT(�����Ȃ�)�h�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�ˁu����.�������v��ԋp����B<BR>
     * �@@�@@�@@�|��L�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�ˁu����.�������̗��c�Ɠ��v��ԋp����B<BR>
     * �@@�@@�Q�|�Q�j��L�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@��O��throw����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@: BUSINESS_ERROR_02865<BR>
     * <BR>
     * @@param l_datOrderBizDate - (������)<BR>
     * ������<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_strDeliveryDateSetDiv - (��n���ݒ�敪)<BR>
     * ��n���ݒ�敪<BR>
     * @@return Date
     * @@throws WEB3BaseException
     */
    public Date getDeliveryDate(
        Date l_datOrderBizDate,
        SubAccount l_subAccount,
        String l_strDeliveryDateSetDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDeliveryDate(Date, SubAccount, String)";
        log.entering(STR_METHOD_NAME);

        Date l_datBizDate = null;
        WEB3GentradeBizDate l_gentradeBizDate =
            new WEB3GentradeBizDate(
                new Timestamp(l_datOrderBizDate.getTime()));
        //FX�U�֏����}�X�^.��n���ݒ�敪�������̏ꍇ
        if (WEB3StringTypeUtility.isDigit(l_strDeliveryDateSetDiv))
        {
            //�u����.�������v�Ɂu����.��n���ݒ�敪�v�̒l�𑫂����c�Ɠ���ԋp����
            l_datBizDate =
                l_gentradeBizDate.roll(Integer.parseInt(l_strDeliveryDateSetDiv));
        }
        else
        {
            //FX�U�֏����}�X�^.��n���ݒ�敪�������ȊO�̏ꍇ
            //��n���ݒ�敪���`�̏ꍇ
            if (WEB3DeliveryDateDivDef.SPECIAL_SETTING.equals(l_strDeliveryDateSetDiv))
            {
                //����.�⏕����.getMainAccount.MRF�����J�݋敪
                // = �h0�FDEFAULT(�����Ȃ�)�h�̏ꍇ
                //      �ˁu����.�������v��ԋp����B
                MainAccount l_mainAccount =
                    l_subAccount.getMainAccount();
                MainAccountRow l_mainAccountRow =
                    (MainAccountRow)l_mainAccount.getDataSourceObject();
                if (WEB3MrfAccOpenDivDef.DEFAULT.equals(
                    l_mainAccountRow.getMrfAccOpenDiv()))
                {
                    l_datBizDate = l_datOrderBizDate;
                }
                else
                {
                    //��L�ȊO�̏ꍇ
                    //   �ˁu����.�������̗��c�Ɠ��v��ԋp����B
                    l_datBizDate =
                        l_gentradeBizDate.roll(1);
                }
            }
            else
            {
                //��L�ȊO�̏ꍇ�A��O��throw����B
                log.debug("��n�����s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02865,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "��n�����s���ł��B");
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_datBizDate;
    }

    /**
     * (updateFX�����J�݋敪�X�V�҃R�[�h)
     * �ڋq�}�X�^�[�e�[�u����FX�����J�݋敪�X�V�҃R�[�h��update����B<BR>
     * <BR>
     * �P�j�ڋq�̎擾 <BR>
     * �@@�g���A�J�E���g�}�l�[�W��.get�ڋq()�ɂČڋq�I�u�W�F�N�g���擾����B<BR>
     * �@@<BR>
     * �@@[�����̐ݒ�]<BR>
     * �@@�،���ЃR�[�h�F�@@����.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h�F�@@����.���X�R�[�h<BR>
     * �@@�����R�[�h�F�@@����.�ڋq�R�[�h<BR>
     * <BR>
     * �@@�擾�����ڋq.getDataSourceObject()�ɂ��ڋqParams���擾����B<BR>
     * <BR>
     * �R�j���������C���X�^���X�ɁA�X�V��̃f�[�^���Z�b�g����B<BR>
     * <BR>
     * �@@�X�V����s�̓��e�͉��L���Q�ƁB<BR>
     * <BR>
     * �@@�y��Trade�z�⑫����.DB�X�V <BR>
     * �@@�uFX�����J�݋���_�ڋq�}�X�^�[.xls�v�����[�N�V�[�g<BR>
     * �@@�u(FX�����J�݋���)�ڋq�}�X�^�[_DB�X�V�d�l_2�v���Q��<BR>
     * <BR>
     * �S�j�ڋq��update<BR>
     *  WEB3DataAccessUtility.updateRow()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[updateRow()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@l_row�F�@@�R�j�ɂčX�V��̃f�[�^���Z�b�g�����C���X�^���X<BR>
     * <BR>
     * @@throws WEB3BaseException 
     */
    public void updateFXAccountOpenDivUpdaterCode(
        String l_strInstitutionCode, String l_strBranchCode, 
        String l_strAccountCode, String l_strUpdaterCode) 
            throws WEB3BaseException 
	{
		String STR_METHOD_NAME =
            "updateFXAccountOpenDivLastUpdater(String l_strInstitutionCode," +
            "String l_strBranchCode, String l_strAccountCode, " +
            "String l_strUpdaterCode)";
        log.entering(STR_METHOD_NAME);
        try
        {
            //�P�j�ڋq�̎擾 
            //�@@�g���A�J�E���g�}�l�[�W��.get�ڋq()�ɂČڋq�I�u�W�F�N�g���擾����B 
            //�@@[�����̐ݒ�] 
            //�@@�،���ЃR�[�h�F�@@����.�،���ЃR�[�h 
            //�@@���X�R�[�h�F�@@����.���X�R�[�h 
            //�@@�����R�[�h�F�@@����.�ڋq�R�[�h 
            //�@@�擾�����ڋq.getDataSourceObject()�ɂ��ڋqParams���擾����B
            WEB3GentradeAccountManager l_web3GentradeAccountManager =
                (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
            
            MainAccount l_mainAccount = 
                l_web3GentradeAccountManager.getMainAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
            
            MainAccountRow l_mainAccountRow = 
                (MainAccountRow)l_mainAccount.getDataSourceObject();
            //�R�j���������C���X�^���X�ɁA�X�V��̃f�[�^���Z�b�g����B 
            //�@@�X�V����s�̓��e�͉��L���Q�ƁB 
            //�@@�y��Trade�z�⑫����.DB�X�V 
            //�@@�uFX�����J�݋���_�ڋq�}�X�^�[.xls�v�����[�N�V�[�g�u(FX�����J�݋���)�ڋq�}�X�^�[_DB�X�V�d�l_2�v���Q��
            MainAccountParams l_mainAccountParams = new MainAccountParams(l_mainAccountRow);
            
            //3.1) �e�w�����J�݋敪�X�V�҃R�[�h
            //���� : ����.�X�V�҃R�[�h
            l_mainAccountParams.setFxAccOpenDivLastUpdater(l_strUpdaterCode);

            //3.2) �e�w�����J�݋敪�X�V����
            //���� : ���ݎ���
            l_mainAccountParams.setFxAccOpenUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //3.3) �X�V����
            l_mainAccountParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //�S�j�ڋq��update 
            //WEB3DataAccessUtility.updateRow()���\�b�h���R�[������B 
            //�@@[updateRow()�ɃZ�b�g����p�����[�^] 
            //�@@�@@l_row�F�@@�R�j�ɂčX�V��̃f�[�^���Z�b�g�����C���X�^���X
            WEB3DataAccessUtility.updateRow(l_mainAccountParams);
        }
        catch (DataException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);		
	}

    /**
     * (get�A�b�v���[�h�ŐV����)<BR>
     * ���Y�A�b�v���[�h�t�@@�C���Ɋ֘A����A�b�v���[�h�ŐV�������擾����B  <BR>
     * <BR>
     * �ȉ��̏����Łu�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u���v���������A  <BR>
     * �擾�����s�I�u�W�F�N�g��ԋp����B  <BR>
     * ���R�[�h���Ȃ��ꍇ��null��ԋp����B  <BR>
     * <BR>
     * [����]  <BR>
     * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�،���ЃR�[�h = ����.�،���ЃR�[�h <BR>
     * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�A�b�v���[�h�t�@@�C���h�c = <BR>
     * �@@�@@����.�A�b�v���[�h�t�@@�C���h�c <BR>
     * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�����^�C�v = ����.�����^�C�v <BR>
     * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�f�[�^�L�[ = ����.�f�[�^�L�[  <BR>
     * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.���l�P != "���~"  <BR>
     * �����l�P���r���鎞�A���ڂ�NULL�̏ꍇ�A�������"NULL"��  <BR>
     * �@@�@@�u�������Ă����r����B(SQL���Fnvl(note1,'NULL') != '���~')  <BR>
     * <BR>
     * [�擾��]  <BR>
     * �A�b�v���[�h�J�n�����@@�~���i�Fdesc�j<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strUploadFileID - (�A�b�v���[�h�t�@@�C���h�c)
     * @@param l_strProductType - (�����^�C�v)
     * @@param l_lngDataKey - (�f�[�^�L�[)
     * @@return Object
     * @@throws WEB3BaseException 
     */
    public Object getUploadNewHistory(String l_strInstitutionCode, 
        String l_strUploadFileID, String l_strProductType, long l_lngDataKey) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getUploadNewHistory(String, String, String, long)";
        log.entering(STR_METHOD_NAME );
        List l_lisRecords = null;
        long l_lngProductType = Integer.parseInt(l_strProductType);
        Long l_lngProductType1 = new Long(l_lngProductType);
            
        Long l_lngUpKey = new Long(l_lngDataKey);  
                  
        //�u�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u���v������
        try
        {

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");            
            l_sbWhere.append(" and upload_file_id = ? ");         
            l_sbWhere.append(" and product_type = ? ");           
            l_sbWhere.append(" and upload_key = ? ");
            l_sbWhere.append(" and nvl(note1,'NULL') != ? ");
                
            //�f�[�^�L�[
            Object[] l_objAdministratorUploadWhere = {l_strInstitutionCode, l_strUploadFileID, l_lngProductType1, l_lngUpKey, WEB3AdminFXUploadNoteOneDef.UPLOAD_TERMINATE}; 

            QueryProcessor l_queryProcessor;
                l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    AdministratorUploadRow.TYPE,
                    l_sbWhere.toString(),
                    "upload_start_timestamp desc",
                    null,
                    l_objAdministratorUploadWhere);
     
        }
        catch(DataFindException l_dfex)
        {
            log.debug(l_dfex.getMessage(), l_dfex);
            return null;
        }
        catch (DataNetworkException l_nex) 
        {
            log.error(l_nex.getMessage(), l_nex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nex.getMessage(),
                l_nex);
        }
        catch (DataQueryException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }
        
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //�擾�����s�I�u�W�F�N�g��ԋp����B
        AdministratorUploadRow l_uploadRow = 
            (AdministratorUploadRow)l_lisRecords.get(0); 
            
        log.exiting(STR_METHOD_NAME);
        return l_uploadRow;
        
    }

    /**
     * (createFX�������ꗗ)<BR>
     * �����̏����ɊY������FX�������̈ꗗ���쐬����B<BR>
     * <BR>
     * �P�j�����`�F�b�N <BR>
     * �@@����.FX�V�X�e���R�[�h��null���ǂ����`�F�b�N����<BR>
     * <BR>
     * �Q�j�P�j�̌��ʂɂ��A���L�̂悤�ɕ��򂵁A�������s���B<BR>
     * �Q�|�P�j����.FX�V�X�e���R�[�h��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@this.getFX�����ԍ�()���\�b�h�i����3�j���R�[������B<BR>
     * <BR>
     * �@@[getFX�����ԍ�()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�،���ЃR�[�h�F�@@����.�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h�F�@@����.���X�R�[�h<BR>
     * �@@�@@�ڋq�R�[�h�F�@@����.�ڋq�R�[�h<BR>
     * <BR>
     * �@@null���ԋp���ꂽ�ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �Q�|�Q�j����.FX�V�X�e���R�[�h��null�łȂ��ꍇ�A<BR>
     * �@@�@@�@@�@@this.getFX�����ԍ�()���\�b�h�i����4�j���R�[������B<BR>
     * <BR>
     * �@@[getFX�����ԍ�()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�،���ЃR�[�h�F�@@����.�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h�F�@@����.���X�R�[�h<BR>
     * �@@�@@�ڋq�R�[�h�F�@@����.�ڋq�R�[�h<BR>
     * �@@�@@FX�V�X�e���R�[�h�F�@@����.FX�V�X�e���R�[�h<BR>
     * <BR>
     * �@@null���ԋp���ꂽ�ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �R�jArrayList�𐶐�����B<BR>
     * <BR>
     * �S�j�Q�j�̖߂�l�̗v�f�����A�ȉ��̏������J��Ԃ��B<BR>
     * �@@�S�|�P�jFX�������C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�S�|�Q�j���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�R�[�X�敪 = FX�����ԍ�Params.FX�R�[�X�敪<BR>
     * �@@�@@�����ԍ� = FX�����ԍ�Params.FX�����ԍ�<BR>
     * <BR>
     *�@@�S�|�R�jArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B<BR>
     * <BR>
     * �T�j��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strBranchCode - (���X�R�[�h)
     * @@param l_strAccountCode - (�ڋq�R�[�h)
     * @@param l_strFxSystemCode - (FX�V�X�e���R�[�h)
     * @@return WEB3FXAccInformationUnit[]
     * @@throws WEB3BaseException 
     */
    public WEB3FXAccInformationUnit[] createFXAccInformationUnits(
        String l_strInstitutionCode, 
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strFxSystemCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "createFXAccInformationUnits(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�����`�F�b�N  
        //����.FX�V�X�e���R�[�h��null���ǂ����`�F�b�N���� 
        FxAccountCodeParams[] l_params = null; 
        
        //�Q�|�P�j����.FX�V�X�e���R�[�h��null�̏ꍇ�A 
        //this.getFX�����ԍ�()���\�b�h�i����3�j���R�[������B
        if (l_strFxSystemCode == null)
        {
            l_params = this.getFXAccountCodes(
                l_strInstitutionCode, 
                l_strBranchCode, 
                l_strAccountCode);
        }
        //�Q�|�Q�j����.FX�V�X�e���R�[�h��null�łȂ��ꍇ�A 
        //this.getFX�����ԍ�()���\�b�h�i����4�j���R�[������B 
        else
        {
            l_params = this.getFXAccountCodes(
                l_strInstitutionCode, 
                l_strBranchCode, 
                l_strAccountCode,
                l_strFxSystemCode);
        }
        
        //null���ԋp���ꂽ�ꍇ�Anull��ԋp����B 
        if (l_params == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //�R�jArrayList�𐶐�����B
        List l_lisUnits = new ArrayList();
        
        //�S�j�Q�j�̖߂�l�̗v�f�����A�ȉ��̏������J��Ԃ��B 
        int l_intCnt = l_params.length;
        
        for (int i= 0; i < l_intCnt; i++)
        {
            //�S�|�P�jFX�������C���X�^���X�𐶐�����B
            WEB3FXAccInformationUnit l_unit = new WEB3FXAccInformationUnit();
            
            //�S�|�Q�j���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
            //�R�[�X�敪 = FX�����ԍ�Params.FX�R�[�X�敪
            FxAccountCodeParams l_param = l_params[i];
            l_unit.fxCourseDiv = l_param.getFxCourseDiv();
            
            //�����ԍ� = FX�����ԍ�Params.FX�����ԍ�
            l_unit.fxAccountCode = l_param.getFxAccountCode();
            
            //�S�|�R�jArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B 
            l_lisUnits.add(l_unit);
        }
        
        //�T�j��������ArrayList.toArray()�̖߂�l��ԋp����B
        WEB3FXAccInformationUnit[] l_units = 
            new WEB3FXAccInformationUnit[l_lisUnits.size()];
        l_lisUnits.toArray(l_units);
        
        log.exiting(STR_METHOD_NAME);
        return l_units;                
    }
    
    /**
     * (getFX�����ԍ�)<BR>
     * �����̏����ɊY������FX�����ԍ��̈ꗗ���擾����B<BR>
     * <BR>
     * �P�jFX�V�X�e���R�[�h�擾<BR>
     * <BR>
     * �@@FX�V�X�e���R�[�h = ����.FX�V�X�e���R�[�h<BR>
     * <BR>
     * �Q�jDB����<BR>
     * �@@FX�����ԍ��e�[�u��(fx_account_code)��<BR>
     * �@@�ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h = ����.���X�R�[�h<BR>
     * �@@FX�V�X�e���R�[�h = �P�j�ɂĎ擾����FX�V�X�e���R�[�h<BR>
     * �@@�ڋq�R�[�h = ����.�ڋq�R�[�h<BR>
     * <BR>
     * �@@���@@�ڋq�R�[�h<BR>
     * �@@�@@�i����.�ڋq�R�[�h.length() == 6�j�̏ꍇ�́A�ŏ���6byte�̂ݔ�r����B)<BR>
     * <BR>
     * �R�j�������ʂ�FX�����ԍ�Params�̔z���FX�R�[�X�敪�̏�����<BR>
     * �@@�\�[�g���A�ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strBranchCode - (���X�R�[�h)
     * @@param l_strAccountCode - (�ڋq�R�[�h)
     * @@param l_strFxSystemCode - (FX�V�X�e���R�[�h)
     * @@return FxAccountCodeParams[]
     * @@throws WEB3BaseException 
     */
    public FxAccountCodeParams[] getFXAccountCodes(
        String l_strInstitutionCode, 
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strFxSystemCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getFXAccountCodes(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
         
        if (l_strAccountCode == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        //�P�jFX�V�X�e���R�[�h�擾 
        //�@@FX�V�X�e���R�[�h = ����.FX�V�X�e���R�[�h 
        
        //�Q�jDB���� 
        //�@@FX�����ԍ��e�[�u��(fx_account_code)�� 
        //�@@�ȉ��̏����Ō�������B                
        //�@@[����] 
        //�@@�،���ЃR�[�h = ����.�،���ЃR�[�h 
        //�@@���X�R�[�h = ����.���X�R�[�h 
        //�@@FX�V�X�e���R�[�h = �P�j�ɂĎ擾����FX�V�X�e���R�[�h 
        //�@@�ڋq�R�[�h = ����.�ڋq�R�[�h 
        //�@@���@@�ڋq�R�[�h 
        //�@@�@@�i����.�ڋq�R�[�h.length() == 6�j�̏ꍇ�́A�ŏ���6byte�̂ݔ�r����B) 
        String l_strWhere = null;
        if (l_strAccountCode.length() == 6)
        {
            l_strWhere = " institution_code = ? and branch_code = ?" +
                " and fx_system_code = ? and substr(account_code ,0 ,6) = ? ";
        }
        else
        {
            l_strWhere = " institution_code = ? and branch_code = ?" +
                " and fx_system_code = ? and account_code = ? ";
        }
        
        Object[] l_objValues = {l_strInstitutionCode, l_strBranchCode, l_strFxSystemCode,
            l_strAccountCode};

        //�R�j�������ʂ�FX�����ԍ�Params�̔z���FX�R�[�X�敪�̏����� 
        //�@@�\�[�g���A�ԋp����B
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    FxAccountCodeRow.TYPE,
                    l_strWhere,
                    "fx_course_div asc",
                    null,
                    l_objValues);
        }
        catch (DataException l_ex)
        {
            log.error("__DataException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        FxAccountCodeParams[] l_fxAccountCodeParams = null;
        if(l_lisRows != null && l_lisRows.size() != 0)
        {
            l_fxAccountCodeParams = 
                new FxAccountCodeParams[l_lisRows.size()]; 
            l_lisRows.toArray(l_fxAccountCodeParams);            
        }

        //�R�j�������ʂ�FX�����ԍ�Params�̔z���FX�R�[�X�敪�̏����� 
        //�@@�\�[�g���A�ԋp����B         
        log.exiting(STR_METHOD_NAME);
        return l_fxAccountCodeParams;
    }
    
    /**
     * (get���o���z�敪) <BR>
     * <BR>
     * GFT���ʒʒm�d������.���o���z�� <BR>
     * GFT���ʒʒm�d������.���o���z2���`�F�b�N���A <BR>
     * ���o���敪��ԋp����B <BR>
     * <BR>
     * �P�j�@@�iGFT���ʒʒm�d������.���o���z�@@!=�@@null�@@and <BR>
     * �@@�@@�@@GFT���ʒʒm�d������.���o���z�@@!=�@@0)�@@&& <BR>
     * �@@�@@�@@�iGFT���ʒʒm�d������.���o���z2�@@!=�@@null�@@and <BR>
     * �@@�@@�@@GFT���ʒʒm�d������.���o���z2�@@!=�@@0)�̏ꍇ�A <BR>
     * �@@�@@�@@���o���z�敪�@@=�@@3�F���o���z�Ɠ��o���z2�̗����ɐݒ肳��Ă���ꍇ��ԋp����B<BR>
     * <BR>
     * �Q�j�@@GFT���ʒʒm�d������.���o���z�@@!=�@@null�@@and <BR>
     * �@@�@@�@@GFT���ʒʒm�d������.���o���z�@@!=�@@0�@@�̏ꍇ�A <BR>
     * �@@�@@�@@���o���z�敪�@@=�@@1�F���o���z�ɐݒ肳��Ă���ꍇ�@@��ԋp����B <BR>
     * <BR>
     * �R�j�@@GFT���ʒʒm�d������.���o���z2�@@!=�@@null�@@and <BR>
     * �@@�@@�@@GFT���ʒʒm�d������.���o���z2�@@!=�@@0�@@�̏ꍇ�A <BR>
     * �@@�@@�@@���o���z�敪�@@=�@@2�F���o���z2�ɐݒ肳��Ă���ꍇ�@@��ԋp����B <BR>
     * <BR>
     * @@param l_fxGftResultNoticeTelegramUnit - (GFT���ʒʒm�d������)<BR>
     * GFT���ʒʒm�d������
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 41D0F49D02B3
     */
    public String getCashInOutAmountDiv(
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getCashInOutAmountDiv(WEB3FXGftResultNoticeTelegramUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_fxGftResultNoticeTelegramUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        String l_strCashInOutAmountDiv = null;
        
        //�P�j�@@�iGFT���ʒʒm�d������.���o���z�@@!=�@@null�@@and 
        //�@@     GFT���ʒʒm�d������.���o���z�@@!=�@@0)�@@&& 
        //�@@    �iGFT���ʒʒm�d������.���o���z2�@@!=�@@null�@@and 
        //�@@     GFT���ʒʒm�d������.���o���z2�@@!=�@@0)�̏ꍇ�A 
        if ((l_fxGftResultNoticeTelegramUnit.cashinoutAmt != null &&
            Double.parseDouble(l_fxGftResultNoticeTelegramUnit.cashinoutAmt) != 0) &&
            (l_fxGftResultNoticeTelegramUnit.cashinoutAmt2 != null &&
                Double.parseDouble(l_fxGftResultNoticeTelegramUnit.cashinoutAmt2) != 0))
        {
            //�@@���o���z�敪�@@=�@@3�F���o���z�Ɠ��o���z2�̗����ɐݒ肳��Ă���ꍇ�@@��ԋp����B 
            l_strCashInOutAmountDiv = WEB3AioCashInOutAmountDivDef.CASHIN_OUT_AMOUNT_AND_AMOUNT2;
            
            log.exiting(STR_METHOD_NAME);
            return l_strCashInOutAmountDiv;
        }
        
        //�Q�j�@@GFT���ʒʒm�d������.���o���z�@@!=�@@null�@@and 
        //�@@    GFT���ʒʒm�d������.���o���z�@@!=�@@0�@@�̏ꍇ�A 
        if (l_fxGftResultNoticeTelegramUnit.cashinoutAmt != null &&
            Double.parseDouble(l_fxGftResultNoticeTelegramUnit.cashinoutAmt) != 0)
        {
            //�@@    ���o���z�敪�@@=�@@1�F���o���z�ɐݒ肳��Ă���ꍇ�@@��ԋp����B 
            l_strCashInOutAmountDiv = WEB3AioCashInOutAmountDivDef.CASHIN_OUT_AMOUNT;
            
            log.exiting(STR_METHOD_NAME);
            return l_strCashInOutAmountDiv;
        }
        //�R�j�@@GFT���ʒʒm�d������.���o���z2�@@!=�@@null�@@and 
        //�@@    GFT���ʒʒm�d������.���o���z2�@@!=�@@0�@@�̏ꍇ�A 
        if (l_fxGftResultNoticeTelegramUnit.cashinoutAmt2 != null &&
            Double.parseDouble(l_fxGftResultNoticeTelegramUnit.cashinoutAmt2) != 0)
        {
            //�@@    ���o���z�敪�@@=�@@2�F���o���z2�ɐݒ肳��Ă���ꍇ�@@��ԋp����B
            l_strCashInOutAmountDiv = WEB3AioCashInOutAmountDivDef.CASHIN_OUT_AMOUNT2;
            
            log.exiting(STR_METHOD_NAME);
            return l_strCashInOutAmountDiv;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strCashInOutAmountDiv;
    }

    /**
     * (updateGFT�����J�ݏ�)<BR>
     * SOAP��M���ʂ�GFT�����J�ݏ󋵃e�[�u���̃f�[�^�ɔ��f����B<BR>
     * <BR>
     * �P�jGFT�����J�ݏ�Params�C���X�^���X���擾����B<BR>
     * <BR>
     * �@@this.getGFT�����J�ݏ�()���R�[������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h�F ����.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h�F ����.���X�R�[�h<BR>
     * �@@���ʃR�[�h�F ����.���ʃR�[�h<BR>
     * <BR>
     * �@@��this.getGFT�����J�ݏ�()�̖߂�l��null�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �Q�j�擾����GFT�����J�ݏ�Params��clone�𐶐�����B<BR>
     * <BR>
     * �R�jclone�ɁA��t���ʃR�[�h���Z�b�g����B<BR>
     * <BR>
     * �@@�X�V����s�̓��e�͉��L���Q�ƁB<BR>
     * <BR>
     * �@@�y��Trade�z�⑫����.DB�X�V<BR>
     * �@@�uFX�����J��_GFT�����J�ݏ󋵃e�[�u��.xls�v��<BR>
     * �@@�uGFT�����J�ݏ󋵃e�[�u��_DB�X�V�d�l[SOAP�ڑ����ʍX�V]�v�V�[�g<BR>
     * <BR>
     * �S�jGFT�����J�ݏ󋵂�update<BR>
     * �@@WEB3DataAccessUtility.updateRow()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[updateRow()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@l_row�F�@@�R�j�ɂĎ�t���ʃR�[�h���Z�b�g�����C���X�^���X<BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - ���X�R�[�h<BR>
     * ���X�R�[�h<BR>
     * @@param l_strOrderRequestNumber - ���ʃR�[�h<BR>
     * ���ʃR�[�h<BR>
     * @@param l_strResultCode - ��t���ʃR�[�h<BR>
     * ��t���ʃR�[�h<BR>
     * @@throws WEB3BaseException
     */
    public void updateGFTAccountOpenStatus(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strOrderRequestNumber,
        String l_strResultCode)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateGFTAccountOpenStatus(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        // �P�jGFT�����J�ݏ�Params�C���X�^���X���擾����B
        // �@@this.getGFT�����J�ݏ�()���R�[������B
        // �@@[����]
        // �@@�،���ЃR�[�h�F ����.�،���ЃR�[�h
        // �@@���X�R�[�h�F ����.���X�R�[�h
        // �@@���ʃR�[�h�F ����.���ʃR�[�h
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
            this.getGFTAccountOpenStatus(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strOrderRequestNumber);

        //��this.getGFT�����J�ݏ�()�̖߂�l��null�̏ꍇ�A��O���X���[����B
        if (l_gftAccountOpenStatusParams == null)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���B");
        }
        // �Q�j�擾����GFT�����J�ݏ�Params��clone�𐶐�����B
        GftAccountOpenStatusParams l_gftAccountOpenStatusParamsClone =
            new GftAccountOpenStatusParams(l_gftAccountOpenStatusParams);

        try
        {
            // �R�jclone�ɁA��t���ʃR�[�h���Z�b�g����B
            l_gftAccountOpenStatusParamsClone.setResultCodeSoap(l_strResultCode);

            // �@@�X�V����s�̓��e�͉��L���Q�ƁB
            // �@@�y��Trade�z�⑫����.DB�X�V
            // �@@�uFX�����J��_GFT�����J�ݏ󋵃e�[�u��.xls�v��
            // �@@�uGFT�����J�ݏ󋵃e�[�u��_DB�X�V�d�l[SOAP�ڑ����ʍX�V]�v�V�[�g
            l_gftAccountOpenStatusParamsClone.setLastUpdatedTimestamp(
                GtlUtils.getSystemTimestamp());

            // �S�jGFT�����J�ݏ󋵂�update
            // �@@WEB3DataAccessUtility.updateRow()���\�b�h���R�[������B
            // �@@[updateRow()�ɃZ�b�g����p�����[�^]
            // �@@�@@l_row�F�@@�R�j�ɂĎ�t���ʃR�[�h���Z�b�g�����C���X�^���X
            WEB3DataAccessUtility.updateRow(l_gftAccountOpenStatusParamsClone);
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

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�ڑ�����) <BR>
     * SOAP�ڑ��������s���B <BR>
     * <BR>
     * 1�j����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g���𕪊����A�ڑ�������擾����B <BR>
     * �@@ getEndpointName().split(arg0 : String) <BR>
     * �@@�@@[����] <BR>
     * �@@�@@arg0�F ";" <BR>
     * 2�j�v���L�V�ݒ���s���B <BR>
     *    ���O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g�̕����� == 3�̏ꍇ�� <BR>
     *     �G���h�|�C���g�Ƃ���1�j�Ŏ擾�����ڑ�����̔z���3�v�f���擾����B<BR>
     *   �@@���������f�[�^��3���ڂ̕������"https"���܂܂��ꍇ���� <BR>
     * �@@�@@ ���L�̒ʂ�v���p�e�B�Z�b�g���s���B <BR>
     * �@@�@@�i1�jkey   : "https.proxyHost" <BR>
     * �@@�@@ �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f <BR>
     * �@@�@@�i2�jkey   : "https.proxyPort" <BR>
     * �@@�@@ �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f <BR>
     *   �@@������L�̏����ȊO���� <BR>
     * �@@�@@ ���L�̒ʂ�v���p�e�B�Z�b�g���s���B <BR>
     * �@@�@@�i1�jkey   : "http.proxyHost" <BR>
     * �@@�@@ �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f <BR>
     * �@@�@@�i2�jkey   : "http.proxyPort" <BR>
     * �@@�@@ �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f <BR>
     *    ���O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g�̕����� == 1�̏ꍇ��<BR>
     *     �G���h�|�C���g�Ƃ���1�j�Ŏ擾�����ڑ�����̔z���1�v�f���擾����B<BR>
     *    ����L�̏����ȊO��<BR>
     * �@@   ��O���X���[����B<BR>
     * 3�jSSLAdapterFactory�𐶐����A�N���C�A���g�ؖ������Z�b�g����B<BR>
     * �@@�@@�i1�j����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�|�[�g�^�C�v���𕪊����A�ؖ����t�@@�C���p�X���擾����B<BR>
     * �@@�@@�@@�@@getPortTypeName().split(arg0 : String)<BR>
     * �@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@arg0�F ";"<BR>
     *         ���O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�|�[�g�^�C�v���̕����� == 2�ȊO�̏ꍇ�A<BR>
     * �@@        ��O���X���[����B<BR>
     * �@@�@@�@@�@@tag�F�@@  WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@class:BUSINESS_ERROR_02398<BR>
     * �@@�@@�i2�jSSLAdapterFactory�𐶐�����B<BR>
     * �@@�@@�@@�@@getDefaultFactory();<BR>
     * �@@�@@�i3�j�i2�j�Ő�������SSLAdapterFactory����WLSSLAdapter���擾����B<BR>
     * �@@�@@�@@�@@getSSLAdapter()<BR>
     * �@@�@@�i4�jFileInputStream�C���X�^���X�𐶐����A�N���C�A���g�ؖ����t�@@�C�����̃t���p�X���i�[����B<BR>
     * �@@�@@�@@�@@FileInputStream(arg0 : String);<BR>
     * �@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@arg0�F �i1�j�Ŏ擾�����ؖ����t�@@�C���p�X�̔z���1�v�f<BR>
     * �@@�@@�i5�j�i3�j�Ŏ擾����WLSSLAdapter�ɃN���C�A���g�ؖ����y�уL�[���Z�b�g����B<BR>
     * �@@�@@�@@�@@loadLocalIdentity(arg0 : InputStream, arg1 : char[]);<BR>
     * �@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@arg0�F �i4�j�Ő�������FileInputStream<BR>
     * �@@�@@�@@�@@�@@�@@arg1�F �O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�I�y���[�V������.toCharArray()<BR>
     * �@@�@@�i6�j�i3�j�Ŏ擾����WLSSLAdapter��CA�ؖ����t�@@�C�����̃t���p�X���i�[����B<BR>
     * �@@�@@�@@�@@setTrustedCertificatesFile(arg0 : String);<BR>
     * �@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@arg0�F �i1�j�Ŏ擾�����ؖ����t�@@�C���p�X�̔z���2�v�f<BR>
     * �@@�@@�i7�j�i2�j�Ő�������SSLAdapterFactory�Ƀf�t�H���g��WLSSLAdapter���Z�b�g����B<BR>
     * �@@�@@�@@�@@setDefaultAdapter(arg0 : SSLAdapter);<BR>
     * �@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@arg0�F �i3�j�Ŏ擾����WLSSLAdapter<BR>
     * �@@�@@�i8�j�i1�j�Ő�������SSLAdapterFactory�Ɂi7�j�ŃZ�b�g����WLSSLAdapter���f�t�H���g�ݒ�ɂ���B<BR>
     * �@@�@@�@@�@@setUseDefaultAdapter(arg0 : boolean);<BR>
     * �@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@arg0�F true<BR>
     * <BR>
     * @@param l_rpcParams �O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams
     * @@throws WEB3BaseException
     */
    public void validateSetup(SoapConnectPrefRpcParams l_rpcParams) 
    	throws WEB3BaseException{

      String STR_METHOD_NAME = 
      	"validateSetup(SoapConnectPrefRpcParams)";
      log.entering(STR_METHOD_NAME);

      String l_logMessage = "";
      
      
      //1�j����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g���𕪊����A
      //�ڑ�������擾����B
      //getEndpointName().split(arg0 : String)
      //[����]
      //arg0�F ";"
      String[] l_strEndpointNames = l_rpcParams.getEndpointName().split(";");

      //2�j�v���L�V�ݒ���s���B
      //���O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g�̕����� == 3�̏ꍇ��
      if (l_strEndpointNames.length == 3)
      {
          // ���������f�[�^��3���ڂ̕������"https"���܂܂��ꍇ����
          //  ���L�̒ʂ�v���p�e�B�Z�b�g���s���B
          // �i1�jkey   : "https.proxyHost"
          // �@@�@@ value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f
          // �i2�jkey   : "https.proxyPort"
          //  �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f
          // ������L�̏����ȊO����
          //  ���L�̒ʂ�v���p�e�B�Z�b�g���s���B
          // �i1�jkey   : "http.proxyHost"
          //  �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f
          // �i2�jkey   : "http.proxyPort"
          //  �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f
	      if(l_strEndpointNames[2].trim().indexOf("https") >= 0)
	      {
	          System.setProperty("https.proxyHost", l_strEndpointNames[0].trim());
	          System.setProperty("https.proxyPort", l_strEndpointNames[1].trim());
	      }
	      else
	      {
	          System.setProperty("http.proxyHost", l_strEndpointNames[0].trim());
	          System.setProperty("http.proxyPort", l_strEndpointNames[1].trim());
	      }

      
      }
      //���O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g�̕����� == 1�̏ꍇ��
      else if (l_strEndpointNames.length == 1)
      {
      }
      //����L�̏����ȊO��
      //��O���X���[����B
      else
      {
          l_logMessage = 
              "�O���V�X�e��SOAP�ڑ��v���t�@@�����X(RPC�`��).�G���h�|�C���g��" + 
              "�̃Z�N�V���������A�قȂ��Ă��܂��B\n" +
              "�uproxy-host;proxy-port;protocol�v�ŃZ�b�g���Ă��������B";
          //�O���V�X�e���ڑ��G���[
          throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_02398,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_logMessage
              );
      }

      //3�jSSLAdapterFactory�𐶐����A�N���C�A���g�ؖ������Z�b�g����B
      //�i1�j����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�|�[�g�^�C�v���𕪊����A
      //�ؖ����t�@@�C���p�X���擾����B
      //getPortTypeName().split(arg0 : String)
      //[����]
      //arg0�F ";"
      String[] l_strPortTypeNames = l_rpcParams.getPortTypeName().split(";");

      //���O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�|�[�g�^�C�v���̕����� == 2�ȊO�̏ꍇ�A
      //��O���X���[����B
      if (l_strPortTypeNames.length != 2)
      {
          l_logMessage = 
              "�O���V�X�e��SOAP�ڑ��v���t�@@�����X(RPC�`��).�|�[�g�^�C�v��" + 
              "�̃Z�N�V���������A�قȂ��Ă��܂��B\n" +
              "�u�N���C�A���g�ؖ����̃t���p�X��;CA�ؖ����̃t���p�X���v�ŃZ�b�g���Ă��������B";
          //�O���V�X�e���ڑ��G���[
          throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_02398,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_logMessage
              );
      }

      //�i4�jFileInputStream�C���X�^���X�𐶐����A�N���C�A���g�ؖ����t�@@�C�����̃t���p�X���i�[����B
      try
      {
    	  if (WEB3SSLCertificateCoder.verifyCertificate(l_strPortTypeNames[1]))
    	  {
              //�O���V�X�e���ڑ��G���[
              throw new WEB3BusinessLayerException(
                  WEB3ErrorCatalog.BUSINESS_ERROR_02398,
                  this.getClass().getName() + "." + STR_METHOD_NAME,
                  l_logMessage);
    	  }
    	  sslSocketFactory =
    		  WEB3SSLCertificateCoder.getSSLSocketFactory(l_strPortTypeNames[0], l_rpcParams.getOperationName(), l_strPortTypeNames[1]);
      }
      catch (Exception l_ex)
      {
          l_logMessage = 
              "�������N���C�A���g�ؖ������擾�ł��܂���B";
          //�O���V�X�e���ڑ��G���[
          throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_02398,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_logMessage
              );
      }
    }

    /**
     * (get�ڋq�R�[�h)<BR>
     * �����̏����ɊY������ڋq�R�[�h���擾����B<BR>
     * <BR>
     * �P�jFX�V�X�e���R�[�h�擾<BR>
     * <BR>
     * �@@FX�V�X�e���R�[�h = ����.FX�V�X�e���R�[�h<BR>
     * <BR>
     * �Q�jDB����<BR>
     * �@@FX�����ԍ��e�[�u��(fx_account_code)��<BR>
     * �@@�ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@����.FX�V�X�e���R�[�h��null���A<BR>
     * �@@[null�łȂ��ꍇ]<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h = ����.���X�R�[�h<BR>
     * �@@�@@FX�V�X�e���R�[�h = �P�j�ɂĎ擾����FX�V�X�e���R�[�h<BR>
     * �@@�@@FX�����ԍ� = ����.FX�����ԍ�<BR>
     * <BR>
     * �@@[null�̏ꍇ]<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h = ����.���X�R�[�h<BR>
     * �@@�@@FX�����ԍ� = ����.FX�����ԍ�<BR>
     * <BR>
     * �R�j�Q�j�Ŏ擾�����f�[�^�̌ڋq�R�[�h��ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strFXAccountCode - (FX�����ԍ�)<BR>
     * FX�����ԍ�<BR>
     * @@param l_strFxSystemCode - (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h<BR>
     * @@return String
     * @@throws WEB3BaseException, NotFoundException
     */
    public String getAccountCode(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strFXAccountCode,
        String l_strFxSystemCode) throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME = "getAccountCode(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //DB����
        //  ����.FX�V�X�e���R�[�h��null���A
        //  [null�łȂ��ꍇ]
        //�@@  [����]
        //�@@  �،���ЃR�[�h = ����.�،���ЃR�[�h
        //�@@  ���X�R�[�h = ����.���X�R�[�h
        //�@@  FX�V�X�e���R�[�h = �P�j�ɂĎ擾����FX�V�X�e���R�[�h
        //�@@  FX�����ԍ� = ����.FX�����ԍ�
        //
        //  [null�̏ꍇ]
        //  �@@[����]
        //  �@@�،���ЃR�[�h = ����.�،���ЃR�[�h
        //  �@@���X�R�[�h = ����.���X�R�[�h
        //  �@@FX�����ԍ� = ����.FX�����ԍ�
        StringBuffer l_sbSql = new StringBuffer();
        List l_lisSqlValues = new ArrayList();

        l_sbSql.append(" institution_code = ? ");
        l_lisSqlValues.add(l_strInstitutionCode);

        l_sbSql.append(" and branch_code = ? ");
        l_lisSqlValues.add(l_strBranchCode);

        if (l_strFxSystemCode != null)
        {
            l_sbSql.append(" and fx_system_code = ? ");
            l_lisSqlValues.add(l_strFxSystemCode);
        }

        l_sbSql.append(" and fx_account_code = ? ");
        l_lisSqlValues.add(l_strFXAccountCode);

        List l_lisResults;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisResults = l_queryProcessor.doFindAllQuery(
                FxAccountCodeRow.TYPE,
                l_sbSql.toString(),
                l_lisSqlValues.toArray());
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

        if (l_lisResults.size() == 0)
        {
            log.debug("FX�����ԍ��e�[�u�����擾�ł��܂���ł���");
            log.exiting(STR_METHOD_NAME);
            throw new NotFoundException("FX�����ԍ��e�[�u�����擾�ł��܂���ł���");
        }

        if (l_lisResults.size() > 1)
        {
            log.debug("FX�����ԍ��e�[�u���ɕ�����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�f�[�^�s�����G���[�B");
        }

        String l_strAccountCode = ((FxAccountCodeRow)l_lisResults.get(0)).getAccountCode();

        log.exiting(STR_METHOD_NAME);
        return l_strAccountCode;
    }

    /**
     * (getSOAPTFX��t���ʃR�[�h)<BR>
     * ���ʒʒm�d���ɃZ�b�g�����t���ʃR�[�h���擾����B<BR>
     * <BR>
     * �P�j����.��t���ʃR�[�h�̒l�ɂ��A�Ή������t���ʃR�[�h���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�E����.GFT��t���ʃR�[�h == �h0�h�i����I���j �̏ꍇ�A<BR>
     * �@@�@@�@@�܂��́A����.GFT��t���ʃR�[�h == �h1�h�i����I���i�����������t�s�j�j �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@��t���ʃR�[�h.�h00000000�h�i���������j<BR>
     * <BR>
     * �@@�@@�E����.GFT��t���ʃR�[�h == �h2�h�i�p�����^�G���[�j �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@��t���ʃR�[�h.�h00000609�h�i��L�ȊO�œd�������ɋN������G���[�j<BR>
     * <BR>
     * �@@�@@�E����.GFT��t���ʃR�[�h == �h1001�h�i���p�҃R�[�h�s���j �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@��t���ʃR�[�h.�h00000501�h�i���o�����ɊY������؋������������݂��Ȃ��j<BR>
     * <BR>
     * �@@�@@�E����.GFT��t���ʃR�[�h == �h3�h�i�d���G���[�j �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@��t���ʃR�[�h.�h00000801�h�i��d�����G���[�j<BR>
     * <BR>
     * �@@�@@�E����.GFT��t���ʃR�[�h == �h6001�h�i�ғ����ԊO�G���[�j �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@��t���ʃR�[�h.�h00000105�h�i�z�X�g�������ԊO�j<BR>
     * <BR>
     * �@@�@@�E��L�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@��t���ʃR�[�h.�h00000199�h�i��L�ȊO�Ńz�X�g�V�X�e���ɋN������G���[�j<BR>
     * <BR>
     * �Q�j��t���ʃR�[�h��ԋp����B<BR>
     * @@param l_strAcceptResultCode - (��t���ʃR�[�h)<BR>
     * ��t���ʃR�[�h<BR>
     * @@return String
     */
    public String getSoapTFXAcceptResultCode(String l_strAcceptResultCode)
    {
        final String STR_METHOD_NAME = "getSoapTFXAcceptResultCode(String)";
        log.entering(STR_METHOD_NAME);

        String l_strSoapAcceptResultCode = null;
        //�P�j����.��t���ʃR�[�h�̒l�ɂ��A�Ή������t���ʃR�[�h���Z�b�g����B
        //�E����.GFT��t���ʃR�[�h == �h0�h�i����I���j �̏ꍇ�A
        //�܂��́A����.GFT��t���ʃR�[�h == �h1�h�i����I���i�����������t�s�j�j �̏ꍇ�A
        //��t���ʃR�[�h.�h00000000�h�i���������j
        if (WEB3SoapResultCodeDef.NORMAL.equals(l_strAcceptResultCode)
            || WEB3SoapResultCodeDef.NORMAL_CANNOT_IN.equals(l_strAcceptResultCode))
        {
            l_strSoapAcceptResultCode =
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000;
        }
        //�E����.GFT��t���ʃR�[�h == �h2�h�i�p�����^�G���[�j �̏ꍇ�A
        //��t���ʃR�[�h.�h00000609�h�i��L�ȊO�œd�������ɋN������G���[�j
        else if (WEB3SoapResultCodeDef.PARAM_ERROR.equals(l_strAcceptResultCode))
        {
            l_strSoapAcceptResultCode =
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000609;
        }
        //�E����.GFT��t���ʃR�[�h == �h1001�h�i���p�҃R�[�h�s���j �̏ꍇ�A
        //��t���ʃR�[�h.�h00000501�h�i���o�����ɊY������؋������������݂��Ȃ��j
        else if (WEB3SoapResultCodeDef.USER_CODE_ERROR.equals(l_strAcceptResultCode))
        {
            l_strSoapAcceptResultCode =
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000501;
        }
        //�E����.GFT��t���ʃR�[�h == �h3�h�i�d���G���[�j �̏ꍇ�A
        //��t���ʃR�[�h.�h00000801�h�i��d�����G���[�j
        else if (WEB3SoapResultCodeDef.REPEAT_ERROR.equals(l_strAcceptResultCode))
        {
            l_strSoapAcceptResultCode =
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000801;
        }
        //�E����.GFT��t���ʃR�[�h == �h6001�h�i�ғ����ԊO�G���[�j �̏ꍇ�A
        //��t���ʃR�[�h.�h00000105�h�i�z�X�g�������ԊO�j
        else if (WEB3SoapResultCodeDef.WORK_TIME_OUT_ERROR.equals(l_strAcceptResultCode))
        {
            l_strSoapAcceptResultCode =
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000105;
        }
        //�E��L�ȊO�̏ꍇ�A
        //��t���ʃR�[�h.�h00000199�h�i��L�ȊO�Ńz�X�g�V�X�e���ɋN������G���[�j
        else
        {
            l_strSoapAcceptResultCode =
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000199;
        }

        //�Q�j��t���ʃR�[�h��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strSoapAcceptResultCode;
    }

    /**
     * (sendSOAP���b�Z�[�W)<BR>
     * SOAP�ڑ����s���B<BR>
     * <BR>
     * 1�j�T�[�r�X�����N���X�𐶐�����B<BR>
     * <BR>
     * �@@ WebService_Impl()<BR>
     * <BR>
     * 2�j�T�[�r�X�X�^�u�𐶐�����B<BR>
     * <BR>
     * �@@�@@service.getWebServiceSoap(arg0 : String)<BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@arg0 : �O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�T�[�r�X��<BR>
     * <BR>
     * <BR>
     * 3�j�T�[�r�X�X�^�u�̃v���p�e�B��ݒ肷��B<BR>
     * <BR>
     * �@@�@@�@@�i1�j�v���p�e�B�ɃG���h�|�C���g���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@_setProperty(arg0 : String, arg1 : Object)<BR> 
     * �@@�@@�@@�@@�@@�@@[����]<BR> 
     * �@@�@@�@@�@@�@@�@@�@@arg0�F "javax.xml.rpc.service.endpoint.address"<BR>
     *�@@�@@�@@�@@       arg1�F �|����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g���̕����� == 3�̏ꍇ<BR>
     *                          ����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g���̔z���3�v�f<BR>
     *                      �|����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g���̕����� == 1�̏ꍇ<BR>
     *                          ����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g���̔z���1�v�f<BR>
     * <BR>
     * �@@�@@�@@�i2�j�v���p�e�B�ɐڑ��^�C���A�E�g���Ԃ��Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@_setProperty(arg0 : String, arg1 : Object)<BR>
     * �@@�@@�@@�@@�@@�@@[����]<BR>
     * <BR>
     * <BR>
     * 4�j���N�G�X�g�f�[�^�𐶐����A���b�Z�[�W����M�����{���A�������ʃ��X�|���X�f�[�^��ԋp����B<BR>
     * <BR>
     * �@@������.GFT�˗��d������.�����敪 == "04�F�o��"�̏ꍇ(FX�ւ̐U�ւ̏ꍇ)��<BR>
     * <BR>
     * �@@�@@�@@�i1�jEntryReceiptIn�C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@EntryReceiptIn()<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�iA�jEntryReceiptIn�C���X�^���X�̉ȖڃR�[�h�ɒl���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@setKamokuCd(arg0 : String)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@arg0�F �h1�h�i�a�����j<BR>
     * �@@�@@�@@�@@�@@�@@�iB�jEntryReceiptIn�C���X�^���X�̓����z�ɒl���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@setReptAmt(arg0 : java.math.BigDecimal)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@arg0�F ����.GFT�˗��d������.���o���z<BR>
     * �@@�@@�@@�@@�@@�@@�iC�jEntryReceiptIn�C���X�^���X�̗��p�҃R�[�h�ɒl���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@setRysCd(arg0 : String)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@arg0�F ����.GFT�˗��d������.�������O�C��ID.substring(1)<BR>
     * �@@�@@�@@�@@�@@�@@�iD�jEntryReceiptIn�C���X�^���X�̂v�d�a�T�[�r�X�v���ԍ��ɒl���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@setWebServReqNo(arg0 : String)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@arg0�F ����.GFT�˗��d������.�������O�C��ID.substring(1)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ ����.GFT�˗��d������.DIR��GFT���M����.substring(2)<BR>
     * <BR>
     * �@@�@@�@@�i2�jSOAP���b�Z�[�W�n���h�����Z�b�g����B 
     * <BR>
     * �@@�@@�@@�@@�@@�@@(A)Web �T�[�r�X �|�[�g�̏C�������i�[����I�u�W�F�N�g���쐬 
     * �@@�@@�@@�@@�@@�@@�@@�@@QName( arg0 : l_rpcParams.target_namespace_name, arg1 : l_rpcParams.service_name) 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@[����] 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@arg0�F ����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�^�[�Q�b�g�l�[���X�y�[�X�� 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@arg1�F ����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�T�[�r�X�� 
     * <BR>
     * �@@�@@�@@�@@�@@�@@(B)HandlerRegistry �I�u�W�F�N�g���쐬 
     * <BR>
     * �@@�@@�@@�@@�@@�@@(C)HandlerInfo�ɐݒ肷��config(�FMap)�f�[�^�̍쐬 
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[put()�Ɏw�肷�����] 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@key�F"BranchId" 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@value�F����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams. ���XID 
     * �@@�@@�@@�@@�@@�@@�@@�@@[put()�Ɏw�肷�����] 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@key�F"ConnectDiv" 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@value�F����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams. �ڑ��敪 
     * <BR>
     * �@@�@@�@@�@@�@@�@@(D)SOAP���b�Z�[�W�̃n���h�� �`�F�[��(�FArrayList)���� 
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[add()�Ɏw�肷�����] 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@new HandlerInfo( arg0 : WEB3FXSOAPLogHandler.class, arg1 : map, arg2 : null ) 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@[����] 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@arg0�F WEB3FXSOAPLogHandler.class 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@arg1�F (3)�Ő�������Map�I�u�W�F�N�g 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@arg2�F null 
     * <BR>
     * �@@�@@�@@�@@�@@�@@(E)�n���h�� �`�F�[����o�^ 
     * �@@�@@�@@�@@�@@�@@�@@�@@setHandlerChain( arg0 : portName, arg1 : handlerList ) 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@[����] 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@arg0�F (1)�Ő��������I�u�W�F�N�g 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@arg1�F (4)�Ő�������ArrayList 
     * <BR>
     * �@@�@@�@@�i3�j���b�Z�[�W����M<BR>
     * �@@�@@�@@�@@�@@regSoap_Stub.entryReceipt(arg0 : EntryReceiptIn)<BR>
     * �@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@arg0 : �i1�j�Ő�������EntryReceiptIn�C���X�^���X<BR>
     * <BR>
     * �@@�@@�@@�i4�j�i3�j�Ŏ擾�����������ʃ��X�|���X�f�[�^����A�������ʃR�[�h���擾����B<BR>
     * <BR>
     * <BR>
     * <BR>
     * 5�j4�j�Ŏ擾�����������ʃR�[�h��ԋp����B<BR>
     * <BR>
     * @@param l_fxGftAskingTelegramUnit - (�d������)<BR>
     * �d������<BR>
     * @@param l_rpcParams - (SOAP�v���t�@@�����X)<BR>
     * �O���V�X�e��SOAP�v���t�@@�����X�iRPC�`���jparams<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String sendSoapMessage(
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit,
        SoapConnectPrefRpcParams l_rpcParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "sendSoapMessage("
            + "WEB3FXGftAskingTelegramUnit, SoapConnectPrefRpcParams)";
        log.entering(STR_METHOD_NAME);


        WebServiceSoap l_webServiceSoap_Stub = null;
        String l_strReturnCd = null;
            //4�j�T�[�r�X�����N���X�𐶐�����B
            //WebService_Impl()
            WebService service = new WebService();

            //5�j�T�[�r�X�X�^�u�𐶐�����B
            //service.getWebServiceSoap(arg0 : String)
            //[����]
            //arg0 : �O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�T�[�r�X��
            l_webServiceSoap_Stub =
                (WebServiceSoap)service.getWebService(
                    l_rpcParams.getServiceName());

            //6�j�T�[�r�X�X�^�u�̃v���p�e�B��ݒ肷��B
            //�i1�j�v���p�e�B�ɃG���h�|�C���g���Z�b�g����B
            //_setProperty(arg0 : String, arg1 : Object)
            //[����]
            //arg0�F "javax.xml.rpc.service.endpoint.address"
            //arg1�F 2�j�Ŏ擾�����G���h�|�C���g
            //arg1�F �|����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g���̕����� == 3�̏ꍇ
            //        ����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g���̔z���3�v�f
            //       �|����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g���̕����� == 1�̏ꍇ
            //        ����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g���̔z���1�v�f
            String[] l_strEndpointNames = l_rpcParams.getEndpointName().split(";");
            String l_strProtocolStr = "";
            if (l_strEndpointNames.length == 3)
            {
                l_strProtocolStr = l_strEndpointNames[2];
            }
            else if (l_strEndpointNames.length == 1)
            {
                l_strProtocolStr = l_strEndpointNames[0];
            }
            
            BindingProvider l_bp = (BindingProvider)l_webServiceSoap_Stub;
            l_bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, l_strProtocolStr);

            //�i2�j�v���p�e�B�ɐڑ��^�C���A�E�g���Ԃ��Z�b�g����B
            l_bp.getRequestContext().put(BindingProviderProperties.CONNECT_TIMEOUT, l_rpcParams.response_timeout);
            l_bp.getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, l_rpcParams.response_timeout);

            l_bp.getRequestContext().put(JAXWSProperties.SSL_SOCKET_FACTORY, sslSocketFactory);

            String l_strOpDiv = l_fxGftAskingTelegramUnit.gftOperationDiv;
            //7�j���N�G�X�g�f�[�^�𐶐����A���b�Z�[�W����M�����{���A�������ʃ��X�|���X�f�[�^��ԋp����B
            //������.GFT�˗��d������.�����敪 == "04�F�o��"�̏ꍇ(FX�ւ̐U�ւ̏ꍇ)��
            if (WEB3GftMessageOperationDef.CASH_OUT.equals(l_strOpDiv))
            {
                //�i1�jEntryReceiptIn�C���X�^���X�𐶐�����B
                //EntryReceiptIn()
            	EntryReceiptIn l_entryReceiptIn = new EntryReceiptIn();

                //�iA�jEntryReceiptIn�C���X�^���X�̉ȖڃR�[�h�ɒl���Z�b�g����B
                //setKamokuCd(arg0 : String)
                //[����]
                //arg0�F �h1�h�i�a�����j
                l_entryReceiptIn.setKamokuCd(WEB3KamokuCdDef.DEPOSIT);

                //�iB�jEntryReceiptIn�C���X�^���X�̓����z�ɒl���Z�b�g����B
                //setReptAmt(arg0 : java.math.BigDecimal)
                //[����]
                //arg0�F ����.GFT�˗��d������.���o���z
                l_entryReceiptIn.setReptAmt(
                    new BigDecimal(
                        l_fxGftAskingTelegramUnit.cashinoutAmt));

                //�iC�jEntryReceiptIn�C���X�^���X�̗��p�҃R�[�h�ɒl���Z�b�g����B
                //setRysCd(arg0 : String)
                //[����]
                //arg0�F ����.GFT�˗��d������.�������O�C��ID.substring(1)
                l_entryReceiptIn.setRysCd(
                    l_fxGftAskingTelegramUnit.fxFirstLoginId.substring(1));

                //�iD�jEntryReceiptIn�C���X�^���X�̂v�d�a�T�[�r�X�v���ԍ��ɒl���Z�b�g����B
                //setWebServReqNo(arg0 : String)
                //[����]
                //arg0�F ����.GFT�˗��d������.�������O�C��ID.substring(1)
                //+ ����.GFT�˗��d������.DIR��GFT���M����.substring(2)
                l_entryReceiptIn.setWebServReqNo(
                    l_fxGftAskingTelegramUnit.fxFirstLoginId.substring(1)
                    + l_fxGftAskingTelegramUnit.dirSendTime.substring(2));

                //(2) SOAP���b�Z�[�W�n���h���̃Z�b�g����B
                ThreadLocalSystemAttributesRegistry.setAttribute("branch_id", String.valueOf(l_rpcParams.branch_id));
                ThreadLocalSystemAttributesRegistry.setAttribute("connect_div", String.valueOf(l_rpcParams.connect_div));
                
                Binding l_bd = l_bp.getBinding();
                List l_lisHandlerChain = l_bd.getHandlerChain();
                WEB3FXSOAPMsgHandler l_handler = new WEB3FXSOAPMsgHandler();
                l_lisHandlerChain.add(l_handler);
                l_bd.setHandlerChain(l_lisHandlerChain);
                
                //�i3�j���b�Z�[�W����M
                //regSoap_Stub.entryReceipt(arg0 : EntryReceiptIn)
                //[����]
                //arg0 : �i1�j�Ő�������EntryReceiptIn�C���X�^���X
                EntryReceiptOut l_entryReceiptOut =
                    l_webServiceSoap_Stub.entryReceipt(l_entryReceiptIn);

                //�i4�j�i3�j�Ŏ擾�����������ʃ��X�|���X�f�[�^����A�������ʃR�[�h���擾����B
                l_strReturnCd = l_entryReceiptOut.getReturnCd() + "";
            }

        //8�j7�j�Ŏ擾�����������ʃR�[�h��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strReturnCd;
    }

    /**
     * (validate�U�։\)<BR>
     * ����\���`�F�b�N���s���B<BR>
     * <BR>
     * 1�jFX�V�X�e���敪 = 1�i��OP�V�X�e���j�̏ꍇ<BR>
     * �@@�@@�@@AIO�����}�l�[�W��.validate�敨��������J��()���R�[��<BR>
     * 2�j��L�ȊO�̏ꍇ<BR>
     * �@@�@@�@@AIO�����}�l�[�W��.validate�U�֎���\�i�j���R�[��<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_compFxConditionParams - (��Е�FX�V�X�e������Params)<BR>
     * ��Е�FX�V�X�e������Params<BR>
     * @@throws WEB3BaseException
     */
    public void validateChangePoss(
        SubAccount l_subAccount,
        CompFxConditionParams l_compFxConditionParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateChangePoss(SubAccount, CompFxConditionParams)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_TradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager =
            (WEB3AioOrderManager)l_TradingModule.getOrderManager();

        String l_strFxSystemDiv = l_compFxConditionParams.getFxSystemDiv();
        //FX�V�X�e���敪 = 1�i��OP�V�X�e���j�̏ꍇ
        if (WEB3FxSystemDivDef.FUOP_SYSTEM.equals(l_strFxSystemDiv))
        {
            //AIO�����}�l�[�W��.validate�敨��������J��()���R�[��
            l_orderManager.validateOpenFuturesTradedAccount(l_subAccount);
        }
        else
        {
            //��L�ȊO�̏ꍇ
            //AIO�����}�l�[�W��.validate�U�֎���\�i�j���R�[��
            l_orderManager.validateTransferTradePossible(
                l_subAccount,
                l_compFxConditionParams);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �igetFX���[���A�h���X�j<BR>
     * �����̏����ɊY������FX���[���A�h���X���擾����B<BR>
     * <BR>
     * �P�j�ȉ��̒l���擾����B<BR>
     * <BR>
     * �@@�@@���[���A�h���X�F�@@�ڋq�i*�P�j.email�A�h���X�inull�̏ꍇ�Anull���Z�b�g�j<BR>
     * �@@�@@�i*�P�j����.�⏕����.getMainAccount()�ɂĎ擾
     * �Q�j�P�j�Ŏ擾�����l��ԋp����B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@return String
     */
    public String getFxMailAddress(SubAccount l_subAccount)
    {
        final String STR_METHOD_NAME = "getFxMailAddress(SubAccount)";
        log.entering(STR_METHOD_NAME);

        //���[���A�h���X�F�@@�ڋq�i*�P�j.email�A�h���X�inull�̏ꍇ�Anull���Z�b�g�j
        //�i*�P�j����.�⏕����.getMainAccount()�ɂĎ擾
        MainAccountRow l_mainAccountRow =
            (MainAccountRow)l_subAccount.getMainAccount().getDataSourceObject();
        String l_strFxMailAddress = l_mainAccountRow.getEmailAddress();

        log.exiting(STR_METHOD_NAME);
        return l_strFxMailAddress;
    }

    /**
     * �igetGFTFX���[���A�h���X�j<BR>
     * �ȉ������ɊY������FX���[���A�h���X���擾����B<BR>
     * <BR>
     * �P�jDB����<BR>
     * <BR>
     * �@@�P�|�P�j<BR>
     * �@@�@@FX�ڋq�e�[�u��(fx_account)���ȉ��̏����Ō������AFX���[���A�h���X���擾����B<BR>
     * �@@�@@�����R�[�h���������擾���ꂽ�ꍇ�́A1���ڂ�FX���[���A�h���X���擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = ����.�⏕����.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h = ����.�⏕����.get����X.getBranchCode()<BR>
     * �@@�ڋq�R�[�h = ����.�⏕����.getMainAccount().getAccountCode()<BR>
     * �@@FX�V�X�e���R�[�h in ����.FX�V�X�e���R�[�h�ꗗ<BR>
     * <BR>
     * �@@�P�|�Q�j<BR>
     * �@@�@@�P�|�P�j�Œl���擾�ł��Ȃ������ꍇ�A�ȉ��̒l���擾����B<BR>
     * <BR>
     * �@@�@@���[���A�h���X�F�@@�ڋq�i*�P�j.email�A�h���X�inull�̏ꍇ�Anull���Z�b�g�j<BR>
     * �@@�@@�i*�P�j����.�⏕����.getMainAccount()�ɂĎ擾<BR>
     * <BR>
     * �Q�j�P�j�Ŏ擾�����l��ԋp����B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����
     * @@param l_lisFxSystemCodeLists - (FX�V�X�e���R�[�h�ꗗ)
     * FX�V�X�e���R�[�h�ꗗ
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getGFTFxMailAddress(
        SubAccount l_subAccount,
        ArrayList l_lisFxSystemCodeLists) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getGFTFxMailAddress("
            + "SubAccount, ArrayList)";
        log.entering(STR_METHOD_NAME);

        String l_strFxMailAddress = null;
        MainAccount l_mainAccount = l_subAccount.getMainAccount();
        MainAccountRow l_mainAccountRow =
            (MainAccountRow)l_mainAccount.getDataSourceObject();

        //�P�jDB����
        //�P�|�P�jFX�ڋq�e�[�u��(fx_account)���ȉ��̏����Ō������AFX���[���A�h���X���擾����B
        //�����R�[�h���������擾���ꂽ�ꍇ�́A1���ڂ�FX���[���A�h���X���擾����B
        //[����]
        //�،���ЃR�[�h = ����.�⏕����.�،���ЃR�[�h
        //���X�R�[�h = ����.�⏕����.get����X.getBranchCode()
        //�ڋq�R�[�h = ����.�⏕����.getMainAccount().getAccountCode()
        //FX�V�X�e���R�[�h in ����.FX�V�X�e���R�[�h�ꗗ
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        String l_strAccountCode = l_mainAccount.getAccountCode();
        String l_strInstitutionCode =
            l_subAccount.getInstitution().getInstitutionCode();

        String l_strWhere = " institution_code = ? and branch_code = ?"
            + " and account_code = ? ";

        List l_lisValues = new ArrayList();
        l_lisValues.add(l_strInstitutionCode);
        l_lisValues.add(l_strBranchCode);
        l_lisValues.add(l_strAccountCode);
        if (l_lisFxSystemCodeLists != null && l_lisFxSystemCodeLists.size() != 0)
        {
            l_strWhere += " and fx_system_code in ( ";
            for (int i = 0; i < l_lisFxSystemCodeLists.size(); i++)
            {
                l_lisValues.add(l_lisFxSystemCodeLists.get(i));
                if (i == l_lisFxSystemCodeLists.size() - 1)
                {
                    l_strWhere += " ?";
                }
                else
                {
                    l_strWhere += " ?,";
                }
            }
            l_strWhere += " ) ";
        }

        Object[] l_sqlValues = new Object[l_lisValues.size()];
        l_lisValues.toArray(l_sqlValues);

        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    FxAccountRow.TYPE,
                    l_strWhere,
                    l_sqlValues);
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

        //�P�|�Q�j �P�|�P�j�Œl���擾�ł��Ȃ������ꍇ�A�ȉ��̒l���擾����B
        if (l_lisRows.size() == 0)
        {
            //���[���A�h���X�F�@@�ڋq�i*�P�j.email�A�h���X�inull�̏ꍇ�Anull���Z�b�g�j
            //�i*�P�j����.�⏕����.getMainAccount()�ɂĎ擾
            l_strFxMailAddress = l_mainAccountRow.getEmailAddress();
        }
        else
        {
            l_strFxMailAddress =
                ((FxAccountRow)l_lisRows.get(0)).getFxMailAddress();
        }

        log.exiting(STR_METHOD_NAME);
        return l_strFxMailAddress;
    }

    /**
     * �iisGFT�����J�݁j<BR>
     * �ڋq��GFT�����J�ݍςł��邩�`�F�b�N���s���B<BR>
     * <BR>
     * �P�jFX�ڋq���R�[�h���擾����B<BR>
     * <BR>
     * �@@FX�ڋq�e�[�u��(fx_account)���ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h = ����.���X�R�[�h<BR>
     * �@@�ڋq�R�[�h = ����.�ڋq�R�[�h<BR>
     * �@@FX�V�X�e���R�[�h in ����.FX�V�X�e���R�[�h�ꗗ<BR>
     * <BR>
     * �Q�j�P�j�Ń��R�[�h���擾�ł����ꍇ�́Atrue��ԋp�B<BR>
     * �@@�@@�擾�ł��Ȃ������ꍇ�́Afalse��ԋp�B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * @@param l_lisFxSystemCodeLists - (FX�V�X�e���R�[�h�ꗗ)<BR>
     * FX�V�X�e���R�[�h�ꗗ<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isGFTAccOpen(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        ArrayList l_lisFxSystemCodeLists) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isGFTAccOpen("
            + "String, String, String, ArrayList)";
        log.entering(STR_METHOD_NAME);

        //�P�jFX�ڋq���R�[�h���擾����B
        //FX�ڋq�e�[�u��(fx_account)���ȉ��̏����Ō�������B
        //[����]
        //�،���ЃR�[�h = ����.�،���ЃR�[�h
        //���X�R�[�h = ����.���X�R�[�h
        //�ڋq�R�[�h = ����.�ڋq�R�[�h
        //FX�V�X�e���R�[�h in ����.FX�V�X�e���R�[�h�ꗗ
        String l_strWhere = " institution_code = ? and branch_code = ? "
            + " and account_code = ? ";

        List l_lisValues = new ArrayList();
        l_lisValues.add(l_strInstitutionCode);
        l_lisValues.add(l_strBranchCode);
        l_lisValues.add(l_strAccountCode);
        if (l_lisFxSystemCodeLists != null && l_lisFxSystemCodeLists.size() != 0)
        {
            l_strWhere += " and fx_system_code in ( ";
            for (int i = 0; i < l_lisFxSystemCodeLists.size(); i++)
            {
                l_lisValues.add(l_lisFxSystemCodeLists.get(i));
                if (i == l_lisFxSystemCodeLists.size() - 1)
                {
                    l_strWhere += " ?";
                }
                else
                {
                    l_strWhere += " ?,";
                }
            }
            l_strWhere += " ) ";
        }

        Object[] l_objValues = new Object[l_lisValues.size()];
        l_lisValues.toArray(l_objValues);

        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    FxAccountRow.TYPE,
                    l_strWhere,
                    l_objValues);
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

        //�Q�j�P�j�Ń��R�[�h���擾�ł����ꍇ�́Atrue��ԋp�B
        //�擾�ł��Ȃ������ꍇ�́Afalse��ԋp�B
        if (l_lisRows.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (createFX�������ꗗ)<BR>
     * �����̏����ɊY������FX�������̈ꗗ���쐬����B<BR>
     * <BR>
     * 1)�@@ArreyList�쐬<BR>
     * <BR>
     * 2)�@@�������擾<BR>
     * <BR>
     * �@@�@@2-1�@@����.�d������.�����敪 == "01"�̏ꍇ�ȉ��������s���B<BR>
     * �@@�@@�@@�@@�@@����.�����J�݌��� != null ���A����.�����J�݌���.getAccountIds() != null�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@<2-1-1�@@����.�����J��FX�V�X�e���R�[�h��null�łȂ������ꍇ�A�ȉ����������{����B><BR>
     * �@@�@@�@@�@@�@@�P�DFX�������C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@�Q�D���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������ԍ� = ����.�����J�݌���.getAccountIds()�̖߂�l��1�v�f<BR>
     * �@@�@@�@@�@@�@@�@@�@@���R�[�X�敪 = 1(1���ʉ݃R�[�X)<BR>
     * �@@�@@�@@�@@�@@�R�DArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B<BR>
     * �@@�@@�@@�@@�@@�S�DFX�������C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@�T�D���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������ԍ� = ����.�����J�݌���.getAccountIds()�̖߂�l��2�v�f<BR>
     * �@@�@@�@@�@@�@@�@@�@@���R�[�X�敪 = 2(10���ʉ݃R�[�X)<BR>
     * �@@�@@�@@�@@�@@�U�DArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B<BR>
     * �@@�@@�@@�@@�@@�V�DFX�������C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@�W�D���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������ԍ� = ����.�����J�݌���.getAccountIds()�̖߂�l��3�v�f<BR>
     * �@@�@@�@@�@@�@@�@@�@@���R�[�X�敪 = 3(CFD�R�[�X)<BR>
     * �@@�@@�@@�@@�@@�X�DArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B<BR>
     * �@@�@@�@@�@@�@@�P�O�D2)�̏������I������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@<2-1-2�@@����.��Е�FX�V�X�e������Params.FX�V�X�e���敪��null�������ꍇ�ȉ����������{����B><BR>
     * �@@�@@�@@�@@�@@�P�DFX�������C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@�Q�D���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������ԍ� = ����.�����J�݌���.getAccountIds()�̖߂�l��1�v�f<BR>
     * �@@�@@�@@�@@�@@�@@�@@���R�[�X�敪 = 1(1���ʉ݃R�[�X)<BR>
     * �@@�@@�@@�@@�@@�R�DArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B<BR>
     * �@@�@@�@@�@@�@@�S�DFX�������C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@�T�D���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������ԍ� = ����.�����J�݌���.getAccountIds()�̖߂�l��2�v�f<BR>
     * �@@�@@�@@�@@�@@�@@�@@���R�[�X�敪 = 2(10���ʉ݃R�[�X)<BR>
     * �@@�@@�@@�@@�@@�U�DArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B<BR>
     * �@@�@@�@@�@@�@@�V�D2)�̏������I������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@<2-1-3�@@����.��Е�FX�V�X�e������Params.FX�V�X�e���敪==�h2�h�������ꍇ�A�ȉ����������{����B><BR>
     * �@@�@@�@@�@@�@@�P�DFX�������C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@�Q�D���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������ԍ� = ����.�����J�݌���.getAccountIds()�̖߂�l��1�v�f<BR>
     * �@@�@@�@@�@@�@@�@@�@@���R�[�X�敪 = 3(CFD�R�[�X)<BR>
     * �@@�@@�@@�@@�@@�R�DArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B<BR>
     * �@@�@@�@@�@@�@@�S�D2)�̏������I������B<BR>
     * <BR>
     * �@@�@@2-2�@@����.�d������.�����敪 == "03"�̏ꍇ�ȉ��������s���B<BR>
     * �@@�@@�@@�@@�@@����.�ǉ��J�݌��� != null ���A����.�ǉ��J�݌���.getAccountId() != null�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@<2-2-1�@@����.��Е�FX�V�X�e������Params.FX�V�X�e���敪��null�������ꍇ�A�ȉ����������{����B><BR>
     * �@@�@@�@@�@@�@@�P�DFX�������C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@�Q�D���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������ԍ� = ����.�ǉ��J�݌���.getAccountId()�̖߂�l��1�v�f<BR>
     * �@@�@@�@@�@@�@@�@@�@@���R�[�X�敪 = 1(1���ʉ݃R�[�X)<BR>
     * �@@�@@�@@�@@�@@�R�DArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B<BR>
     * �@@�@@�@@�@@�@@�S�DFX�������C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@�T�D���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������ԍ� = ����.�ǉ��J�݌���.getAccountId()�̖߂�l��2�v�f<BR>
     * �@@�@@�@@�@@�@@�@@�@@���R�[�X�敪 = 2(10���ʉ݃R�[�X)<BR>
     * �@@�@@�@@�@@�@@�U�DArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B<BR>
     * �@@�@@�@@�@@�@@�V�D2)�̏������I������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@<2-2-2�@@����.��Е�FX�V�X�e������Params.FX�V�X�e���敪==�h2�h�������ꍇ�A�ȉ����������{����B><BR>
     * �@@�@@�@@�@@�@@�P�DFX�������C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@�Q�D���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������ԍ� = ����.�ǉ��J�݌���.getAccountId()�̖߂�l��1�v�f<BR>
     * �@@�@@�@@�@@�@@�@@�@@���R�[�X�敪 = 3(CFD�R�[�X)<BR>
     * �@@�@@�@@�@@�@@�R�DArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B<BR>
     * �@@�@@�@@�@@�@@�S�D2)�̏������I������B<BR>
     * <BR>
     * 3)�@@��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * <BR>
     * @@param l_fxGftAskingTelegramUnit - (�d������)<BR>
     * GFT�˗��d������<BR>
     * @@param l_resultInfoCreateUser - (�����J�݌���)<BR>
     * �����J�݌���<BR>
     * @@param l_strSameTimeFxSystemCode - (�����J��FX�V�X�e���R�[�h)<BR>
     * �����J��FX�V�X�e���R�[�h<BR>
     * @@param l_compFxConditionParams - (��Е�FX�V�X�e������Params)<BR>
     * ��Е�FX�V�X�e������Params<BR>
     * @@param l_resultInfoAddAccount - (�ǉ��J�݌���)<BR>
     * �ǉ��J�݌���<BR>
     * @@return WEB3FXAccInformationUnit[]
     */
    public WEB3FXAccInformationUnit[] createFXAccInformationUnits(
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit,
        ResultInfoCreateUser l_resultInfoCreateUser,
        String l_strSameTimeFxSystemCode,
        CompFxConditionParams l_compFxConditionParams,
        ResultInfoAddAccount l_resultInfoAddAccount)
    {
        final String STR_METHOD_NAME = "createFXAccInformationUnits("
            + "WEB3FXGftAskingTelegramUnit, ResultInfoCreateUser,"
            + "String, CompFxConditionParams, ResultInfoAddAccount)";
        log.entering(STR_METHOD_NAME);

        WEB3FXAccInformationUnit[] l_fxAccInformationUnits = null;
        WEB3FXAccInformationUnit l_fxAccInformationUnit = null;
        //1)�@@ArreyList�쐬
        ArrayList l_lisRecord = new ArrayList();

        String l_strFxSystemDiv = l_compFxConditionParams.getFxSystemDiv();
        String l_strGftOperationDiv = l_fxGftAskingTelegramUnit.gftOperationDiv;
        //2)�@@�������擾
        //2-1�@@����.�d������.�����敪 == "01"�̏ꍇ�ȉ��������s���B
        if (WEB3AdminAioGftOperationDivDef.ACCOUNT_OPEN.equals(
            l_strGftOperationDiv))
        {
            //����.�����J�݌��� != null ���A����.�����J�݌���.getAccountIds() != null�̏ꍇ
            if (l_resultInfoCreateUser != null
                && l_resultInfoCreateUser.getAccountIds() != null)
            {
                //<2-1-1�@@����.�����J��FX�V�X�e���R�[�h��null�łȂ������ꍇ�A�ȉ����������{����B>
                if (!WEB3StringTypeUtility.isEmpty(l_strSameTimeFxSystemCode))
                {
                    //�P�DFX�������C���X�^���X�𐶐�����B
                	l_fxAccInformationUnit = new WEB3FXAccInformationUnit();

                    //�Q�D���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
                    //�������ԍ� = ����.�����J�݌���.getAccountIds()�̖߂�l��1�v�f
                    //���R�[�X�敪 = 1(1���ʉ݃R�[�X)
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoCreateUser.getAccountIds().get(0) + "";
                    l_fxAccInformationUnit.fxCourseDiv =
                        WEB3GftTransStatusCourseDivDef.ONE_COSE;

                    //�R�DArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B
                    l_lisRecord.add(l_fxAccInformationUnit);

                    //�S�DFX�������C���X�^���X�𐶐�����B
                    l_fxAccInformationUnit = new WEB3FXAccInformationUnit();

                    //�T�D���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
                    //�������ԍ� = ����.�����J�݌���.getAccountIds()�̖߂�l��2�v�f
                    //���R�[�X�敪 = 2(10���ʉ݃R�[�X)
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoCreateUser.getAccountIds().get(1) + "";
                    l_fxAccInformationUnit.fxCourseDiv =
                        WEB3GftTransStatusCourseDivDef.TEN_COSE;

                    //�U�DArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B
                    l_lisRecord.add(l_fxAccInformationUnit);

                    //�V�DFX�������C���X�^���X�𐶐�����B
                    l_fxAccInformationUnit = new WEB3FXAccInformationUnit();

                    //�W�D���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
                    //�������ԍ� = ����.�����J�݌���.getAccountIds()�̖߂�l��3�v�f
                    //���R�[�X�敪 = 3(CFD�R�[�X)
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoCreateUser.getAccountIds().get(2) + "";
                    l_fxAccInformationUnit.fxCourseDiv =
                        WEB3GftTransStatusCourseDivDef.CFD_COURSE;

                    //�X�DArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B
                    l_lisRecord.add(l_fxAccInformationUnit);
                }

                //<2-1-2�@@����.��Е�FX�V�X�e������Params.FX�V�X�e���敪��null�������ꍇ�A
                //�ȉ����������{����B>
                else if (WEB3StringTypeUtility.isEmpty(l_strFxSystemDiv))
                {
                    //�P�DFX�������C���X�^���X�𐶐�����B
                	l_fxAccInformationUnit = new WEB3FXAccInformationUnit();

                    //�Q�D���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
                    //�������ԍ� = ����.�����J�݌���.getAccountIds()�̖߂�l��1�v�f
                    //���R�[�X�敪 = 1(1���ʉ݃R�[�X)
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoCreateUser.getAccountIds().get(0) + "";
                    l_fxAccInformationUnit.fxCourseDiv =
                        WEB3GftTransStatusCourseDivDef.ONE_COSE;

                    //�R�DArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B
                    l_lisRecord.add(l_fxAccInformationUnit);

                    //�S�DFX�������C���X�^���X�𐶐�����B
                    l_fxAccInformationUnit = new WEB3FXAccInformationUnit();

                    //�T�D���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
                    //�������ԍ� = ����.�����J�݌���.getAccountIds()�̖߂�l��2�v�f
                    //���R�[�X�敪 = 2(10���ʉ݃R�[�X)
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoCreateUser.getAccountIds().get(1) + "";
                    l_fxAccInformationUnit.fxCourseDiv =
                        WEB3GftTransStatusCourseDivDef.TEN_COSE;

                    //�U�DArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B
                    l_lisRecord.add(l_fxAccInformationUnit);
                }

                //<2-1-3�@@����.��Е�FX�V�X�e������Params.FX�V�X�e���敪==�h2�h�������ꍇ�A
                //�ȉ����������{����B>
                else if (WEB3FxSystemDivDef.CFD_SYSTEM.equals(l_strFxSystemDiv))
                {
                    //�P�DFX�������C���X�^���X�𐶐�����B
                	l_fxAccInformationUnit = new WEB3FXAccInformationUnit();

                    //�Q�D���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
                    //�������ԍ� = ����.�����J�݌���.getAccountIds()�̖߂�l��1�v�f
                    //���R�[�X�敪 = 3(CFD�R�[�X)
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoCreateUser.getAccountIds().get(0) + "";
                    l_fxAccInformationUnit.fxCourseDiv =
                        WEB3GftTransStatusCourseDivDef.CFD_COURSE;

                    //�R�DArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B
                    l_lisRecord.add(l_fxAccInformationUnit);
                }
            }
        }

        //2-2�@@����.�d������.�����敪 == "03"�̏ꍇ�ȉ��������s���B
        //����.�ǉ��J�݌��� != null ���A����.�ǉ��J�݌���.getAccountId() != null�̏ꍇ
        else if (WEB3AdminAioGftOperationDivDef.ADD_ACCOUNT.equals(
            l_strGftOperationDiv))
        {
            //����.�ǉ��J�݌��� != null ���A����.�ǉ��J�݌���.getAccountIds() != null�̏ꍇ
            if (l_resultInfoAddAccount != null
                && l_resultInfoAddAccount.getAccountId() != null)
            {
                //<2-2-1�@@����.��Е�FX�V�X�e������Params.FX�V�X�e���敪��null�������ꍇ�A
                //�ȉ����������{����B>
                if (WEB3StringTypeUtility.isEmpty(l_strFxSystemDiv))
                {
                    //�P�DFX�������C���X�^���X�𐶐�����B
                	l_fxAccInformationUnit = new WEB3FXAccInformationUnit();

                    //�Q�D���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
                    //�������ԍ� = ����.�ǉ��J�݌���.getAccountId()�̖߂�l��1�v�f
                    //���R�[�X�敪 = 1(1���ʉ݃R�[�X)
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoAddAccount.getAccountId().get(0) + "";
                    l_fxAccInformationUnit.fxCourseDiv =
                        WEB3GftTransStatusCourseDivDef.ONE_COSE;

                    //�R�DArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B
                    l_lisRecord.add(l_fxAccInformationUnit);

                    //�S�DFX�������C���X�^���X�𐶐�����B
                    l_fxAccInformationUnit = new WEB3FXAccInformationUnit();

                    //�T�D���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
                    //�������ԍ� = ����.�ǉ��J�݌���.getAccountId()�̖߂�l��2�v�f
                    //���R�[�X�敪 = 2(10���ʉ݃R�[�X)
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoAddAccount.getAccountId().get(1) + "";
                    l_fxAccInformationUnit.fxCourseDiv =
                        WEB3GftTransStatusCourseDivDef.TEN_COSE;

                    //�U�DArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B
                    l_lisRecord.add(l_fxAccInformationUnit);
                }

                //<2-2-2�@@����.��Е�FX�V�X�e������Params.FX�V�X�e���敪==�h2�h�������ꍇ�A
                //�ȉ����������{����B>
                else if (WEB3FxSystemDivDef.CFD_SYSTEM.equals(l_strFxSystemDiv))
                {
                    //�P�DFX�������C���X�^���X�𐶐�����B
                	l_fxAccInformationUnit =
                        new WEB3FXAccInformationUnit();

                    //�Q�D���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
                    //�������ԍ� = ����.�ǉ��J�݌���.getAccountId()�̖߂�l��1�v�f
                    //���R�[�X�敪 = 3(CFD�R�[�X)
                	l_fxAccInformationUnit.fxAccountCode = l_resultInfoAddAccount.getAccountId().get(0) + "";
                	l_fxAccInformationUnit.fxCourseDiv =
                        WEB3GftTransStatusCourseDivDef.CFD_COURSE;

                    //�R�DArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B
                    l_lisRecord.add(l_fxAccInformationUnit);
                }
            }
        }

        //3)�@@��������ArrayList.toArray()�̖߂�l��ԋp����B
        l_fxAccInformationUnits =
            new WEB3FXAccInformationUnit[l_lisRecord.size()];
        l_lisRecord.toArray(l_fxAccInformationUnits);

        log.exiting(STR_METHOD_NAME);
        return l_fxAccInformationUnits;
    }

    /**
     * (get�����J��FX�V�X�e���R�[�h)<BR>
     * �����J�݂���FX�V�X�e���R�[�h���擾����B<BR>
     * <BR>
     * �P�j�،����ID���擾����B
     * <BR>
     * �Q�j�ȉ��̏����ŁA�،���Ѓv���t�@@�����X�e�[�u�����烌�R�[�h���擾����B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�،����ID = �P�j�Ŏ擾�����،����ID<BR>
     * �@@�@@�v���t�@@�����X�� = "gft.accountopen.fxsystemcode"<BR>
     * <BR>
     * �R�j���R�[�h���擾�ł����ꍇ�̓v���t�@@�����X�̒l���A<BR>
     * �@@�@@�ł��Ȃ������ꍇ��null���擾����B<BR>
     * <BR>
     * �S�j�擾�����l��ԋp<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getSameTimeFxSystemCode(String l_strInstitutionCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSameTimeFxSystemCode(String)";
        log.entering(STR_METHOD_NAME);

        //�g���A�J�E���g�}�l�[�W���擾����
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        //�P�j�،����ID���擾����B
        String l_strInstitutionId = null;
        try{
            Institution l_Instituion =
                l_accountManager.getInstitution(l_strInstitutionCode);
            l_strInstitutionId = String.valueOf(l_Instituion.getInstitutionId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j�ȉ��̏����ŁA�،���Ѓv���t�@@�����X�e�[�u�����烌�R�[�h���擾����B
        //[����]
        //�،����ID = �P�j�Ŏ擾�����،����ID
        //�v���t�@@�����X�� = "gft.accountopen.fxsystemcode"
        String l_strWhere = " institution_id = ? and name = ? ";

        String l_strValue = null;
        Object[] l_objValue =
            new Object[]{
                l_strInstitutionId,
                WEB3InstitutionPreferencesNameDef.GFT_ACCOUNTOPEN_FXSYSTEMCODE};

        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    InstitutionPreferencesRow.TYPE,
                    l_strWhere,
                    l_objValue);
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

        //�R�j���R�[�h���擾�ł����ꍇ�̓v���t�@@�����X�̒l���A
        //�ł��Ȃ������ꍇ��null���擾����B
        if (l_lisRows.size() != 0)
        {
            l_strValue = ((InstitutionPreferencesRow)l_lisRows.get(0)).getValue();
        }

        //�S�j�擾�����l��ԋp
        log.exiting(STR_METHOD_NAME);
        return l_strValue;
    }

    /**
     * (getGFTFX�V�X�e���R�[�h�ꗗ)<BR>
     * �O���ڑ��V�X�e���R�[�h ��GFT�́A�S�Ă�FX�V�X�e���R�[�h���擾����B<BR>
     * <BR>
     * �P�j�@@FX�V�X�e���R�[�h�擾<BR>
     * �@@��Е�FX�V�X�e�������e�[�u�����ȉ��̏����Ō������AFX�V�X�e���R�[�h���擾����B<BR>
     * <BR>
     * [����]<BR>
     * �@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h = ����.���X�R�[�h<BR>
     * �@@�O���ڑ��V�X�e���R�[�h = 01�FGFT <BR>
     * <BR>
     * �Q�j�@@�P�j�Ŏ擾����FX�V�X�e���R�[�h��ArreyList�ŕԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@return ArrayList
     * @@throws WEB3BaseException
     */
    public ArrayList getGFTFxSystemCodeLists(
        String l_strInstitutionCode,
        String l_strBranchCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getGFTFxSystemCodeLists(String, String)";
        log.entering(STR_METHOD_NAME);

        ArrayList l_lisFxSystemCodes = new ArrayList();
        List l_lisRows = null;
        //1�j�@@FX�V�X�e���R�[�h�擾
        //��Е�FX�V�X�e�������e�[�u�����ȉ��̏����Ō������AFX�V�X�e���R�[�h���擾����B
        //[����]
        //�،���ЃR�[�h = ����.�،���ЃR�[�h
        //���X�R�[�h = ����.���X�R�[�h
        //�O���ڑ��V�X�e���R�[�h = 01�FGFT
        String l_strWhere =
            " institution_code = ? and branch_code = ? and ext_connect_system_code = ? ";

        Object[] l_sqlValues =
            new Object[]{
                l_strInstitutionCode,
                l_strBranchCode,
                WEB3ExtConnectSystemCodeDef.GFT};

        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    CompFxConditionRow.TYPE,
                    l_strWhere,
                    l_sqlValues);
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

        //2�j�@@1�j�Ŏ擾����FX�V�X�e���R�[�h��ArreyList�ŕԋp����B
        Iterator l_iterator = l_lisRows.iterator();
        while (l_iterator.hasNext())
        {
            l_lisFxSystemCodes.add(
                ((CompFxConditionRow)l_iterator.next()).getFxSystemCode());
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisFxSystemCodes;
    }

    /**
     * (insert���������J��)<BR>
     * GFT���ʒʒm�d���̓��e��FX�ڋq�e�[�u��(fx_account)�ɍs��insert���s���B<BR>
     * <BR>
     * �}������s�̓��e�͉��L���Q�ƁB<BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V<BR>
     * �uFX�����J��_FX�ڋq�e�[�u��.xls�v<BR>
     * ((FX�����J��)FX�ڋq�e�[�u��_DB�X�V�d�l (�����J�݁j)<BR>
     * @@param l_fxGftResultNoticeTelegramUnit - (GFT���ʒʒm�d������)<BR>
     * GFT���ʒʒm�d������<BR>
     * @@param l_gftAccountOpenStatusParams - (GFT�����J�ݏ�Params)<BR>
     * GFT�����J�ݏ�Params<BR>
     * @@param l_strSimultaneousFxSystemCode - (�����J��FX�V�X�e���R�[�h)<BR>
     * �����J��FX�V�X�e���R�[�h<BR>
     * @@throws WEB3BaseException
     */
    public void insertSimultaneousAccountOpen(
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit,
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams,
        String l_strSimultaneousFxSystemCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertSimultaneousAccountOpen(WEB3FXGftResultNoticeTelegramUnit, GftAccountOpenStatusParams, String)";
        log.entering(STR_METHOD_NAME);

        //�}������s�̓��e�͉��L���Q��
        //�y��Trade�z�⑫����.DB�X�V
        //�uFX�����J��_FX�ڋq�e�[�u��.xls�v
        // ((FX�����J��)FX�ڋq�e�[�u��_DB�X�V�d�l (�����J�݁j
        FxAccountParams l_fxAccountParams =
            new FxAccountParams();

        //FX�ڋqID
        //FX�f�[�^����T�[�r�XImpl.get�V�KFX�ڋqID(this.�،���ЃR�[�h, this.���X�R�[�h,
        //this.�ڋq�R�[�h�A����.�����J��FX�V�X�e���R�[�h)
        String l_strFXAccountID =  this.getNewFXAccountID(
            l_gftAccountOpenStatusParams.getInstitutionCode(),
            l_gftAccountOpenStatusParams.getBranchCode(),
            l_gftAccountOpenStatusParams.getAccountCode(),
            l_strSimultaneousFxSystemCode);

        l_fxAccountParams.setFxAccountId(Long.parseLong(l_strFXAccountID));

        //�،���ЃR�[�h:����.GFT���ʒʒm�d������.��ЃR�[�h
        l_fxAccountParams.setInstitutionCode(
            l_fxGftResultNoticeTelegramUnit.institutionCode);

        //���X�R�[�h:����.GFT���ʒʒm�d������.���X�R�[�h
        l_fxAccountParams.setBranchCode(
            l_fxGftResultNoticeTelegramUnit.branchCode);

        //FX�V�X�e���R�[�h:����.�����J��FX�V�X�e���R�[�h
        l_fxAccountParams.setFxSystemCode(l_strSimultaneousFxSystemCode);

        //�ڋq�R�[�h
        //����.GFT���ʒʒm�d������.�ڋq�R�[�h
        l_fxAccountParams.setAccountCode(
            l_fxGftResultNoticeTelegramUnit.accountCode);

        //FX�����敪:1�F�J�ݍ�
        l_fxAccountParams.setFxAccountDiv(WEB3AioAccountDivDef.OPEN_COMPLETE);

        //���O�i���j:GFT�����J�ݏ�Params.���O�i���j
        l_fxAccountParams.setFxLastName(l_gftAccountOpenStatusParams.getLastName());

        //���O�i���j:GFT�����J�ݏ�Params.���O�i���j
        l_fxAccountParams.setFxFirstName(l_gftAccountOpenStatusParams.getFirstName());

        //FX���[���A�h���X:����.GFT���ʒʒm�d������.���[���A�h���X
        l_fxAccountParams.setFxMailAddress(l_fxGftResultNoticeTelegramUnit.fxMailAddress);

        //FX���O�C��ID:����.GFT���ʒʒm�d������.�������O�C��ID
        l_fxAccountParams.setFxLoginId(Long.parseLong(l_fxGftResultNoticeTelegramUnit.fxFirstLoginId));

        //�X�V�҃R�[�h:null
        l_fxAccountParams.setLastUpdater(null);

        //�쐬���t:
        //���ݎ���
        Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
        l_fxAccountParams.setCreatedTimestamp(l_tsSystemTime);

        //�X�V���t:
        //���ݎ���
        l_fxAccountParams.setLastUpdatedTimestamp(l_tsSystemTime);

        try
        {
            WEB3DataAccessUtility.insertRow(l_fxAccountParams);
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

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insert���������J��)<BR>
     * GFT���ʒʒm�d���̓��e��FX�ڋq�e�[�u��(fx_account)�ɍs��insert���s���B<BR>
     * <BR>
     * �}������s�̓��e�͉��L���Q�ƁB<BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V<BR>
     * �u�Ǘ��ҁE�����J�݊Ǘ�_FX�ڋq�e�[�u��DB�X�V�d�l.xls�v<BR>
     * ((�����J�݊Ǘ�)FX�ڋq�e�[�u��DB�X�V�d�l (�����J��))<BR>
     * @@param l_gftAccountOpenStatusParams - (GFT�����J�ݏ�Params)<BR>
     * GFT�����J�ݏ�Params<BR>
     * @@param l_strSimultaneousFxSystemCode - (�����J��FX�V�X�e���R�[�h)<BR>
     * �����J��FX�V�X�e���R�[�h<BR>
     * @@param l_strUpdaterCode - (�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h<BR>
     * @@throws WEB3BaseException
     */
    public void insertSimultaneousAccountOpen(
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams,
        String l_strSimultaneousFxSystemCode,
        String l_strUpdaterCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertSimultaneousAccountOpen(GftAccountOpenStatusParams, String, String)";
        log.entering(STR_METHOD_NAME);

        //�}������s�̓��e�͉��L���Q��
        //�y��Trade�z�⑫����.DB�X�V
        //�Ǘ��ҁE�����J�݊Ǘ�_FX�ڋq�e�[�u��DB�X�V�d�l.xls�v
        // ((�����J�݊Ǘ�)FX�ڋq�e�[�u��DB�X�V�d�l (�����J��))
        FxAccountParams l_fxAccountParams =
            new FxAccountParams();

        //FX�ڋqID:
        //FX�f�[�^����T�[�r�XImpl.get�V�KFX�ڋqID(this.�،���ЃR�[�h, this.���X�R�[�h,
        //this.�ڋq�R�[�h�A�����J��FX�V�X�e���R�[�h)
        String l_strFXAccountID =  this.getNewFXAccountID(
            l_gftAccountOpenStatusParams.getInstitutionCode(),
            l_gftAccountOpenStatusParams.getBranchCode(),
            l_gftAccountOpenStatusParams.getAccountCode(),
            l_strSimultaneousFxSystemCode);

        l_fxAccountParams.setFxAccountId(Long.parseLong(l_strFXAccountID));

        //�،���ЃR�[�h:GFT�����J�ݏ�Params.�،���ЃR�[�h
        l_fxAccountParams.setInstitutionCode(
            l_gftAccountOpenStatusParams.getInstitutionCode());

        //���X�R�[�h:GFT�����J�ݏ�Params.���X�R�[�h
        l_fxAccountParams.setBranchCode(
            l_gftAccountOpenStatusParams.getBranchCode());

        //FX�V�X�e���R�[�h:�����J��FX�V�X�e���R�[�h
        l_fxAccountParams.setFxSystemCode(
            l_strSimultaneousFxSystemCode);

        //�ڋq�R�[�h:
        //GFT�����J�ݏ�Params.�ڋq�R�[�h
        l_fxAccountParams.setAccountCode(
            l_gftAccountOpenStatusParams.getAccountCode());

        //FX�����敪:1�F�J�ݍ�
        l_fxAccountParams.setFxAccountDiv(WEB3AioAccountDivDef.OPEN_COMPLETE);

        //���O�i���j:GFT�����J�ݏ�Params.���O�i���j
        l_fxAccountParams.setFxLastName(l_gftAccountOpenStatusParams.getLastName());

        //���O�i���j:GFT�����J�ݏ�Params.���O�i���j
        l_fxAccountParams.setFxFirstName(l_gftAccountOpenStatusParams.getFirstName());

        //FX���[���A�h���X:GFT�����J�ݏ�Params.GFT���[���A�h���X
        l_fxAccountParams.setFxMailAddress(l_gftAccountOpenStatusParams.getMailAddress());

        //FX���O�C��ID:GFT�����J�ݏ�Params.GFT���O�C��ID
        l_fxAccountParams.setFxLoginId(Long.parseLong(l_gftAccountOpenStatusParams.getLoginId()));

        //�X�V�҃R�[�h
        //����.�X�V�҃R�[�h
        l_fxAccountParams.setLastUpdater(l_strUpdaterCode);

        //�쐬���t
        //���ݎ���
        Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
        l_fxAccountParams.setCreatedTimestamp(l_tsSystemTime);

        //�X�V���t
        //���ݎ���
        l_fxAccountParams.setLastUpdatedTimestamp(l_tsSystemTime);

        try
        {
            WEB3DataAccessUtility.insertRow(l_fxAccountParams);
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

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insertFX�����ԍ�)<BR>
     * GFT�����J�ݏ�Params�AFX�������̓��e���A<BR>
     * FX�����ԍ��e�[�u��(fx_account_code)�ɍs��insert���s���B<BR>
     * <BR>
     * �}������s�̓��e�͉��L���Q�ƁB<BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V<BR>
     * �u�Ǘ��ҁE�����J�݊Ǘ�_FX�����ԍ��e�[�u��DB�X�V�d�l.xls<BR>
     * (�����J�݊Ǘ�)FX�����ԍ��e�[�u��DB�X�V�d�l�@@���������J�݁v<BR>
     * @@param l_gftAccountOpenStatusParams - (GFT�����J�ݏ�Params)<BR>
     * GFT�����J�ݏ�Params<BR>
     * @@param l_fxAccInformation - (FX�������)<BR>
     * FX�������<BR>
     * @@param l_strUpdateCode - (�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h<BR>
     * @@param l_strSimultaneousFxSystemCode - (�����J��FX�V�X�e���R�[�h)<BR>
     * �����J��FX�V�X�e���R�[�h<BR>
     * @@throws WEB3BaseException
     */
    public void insertFXAccountCode(
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams,
        WEB3FXAccInformationUnit l_fxAccInformation,
        String l_strUpdateCode,
        String l_strSimultaneousFxSystemCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertFXAccountCode(GftAccountOpenStatusParams, WEB3FXAccInformationUnit, String, String)";
        log.entering(STR_METHOD_NAME);

        if(l_fxAccInformation == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�}������s�̓��e�͉��L���Q��
        //�y��Trade�z�⑫����.DB�X�V
        //�u�Ǘ��ҁE�����J�݊Ǘ�_FX�����ԍ��e�[�u��DB�X�V�d�l.xls
        // (�����J�݊Ǘ�)FX�����ԍ��e�[�u��DB�X�V�d�l�@@���������J�݁v
        FxAccountCodeParams l_fxAccountCodeParams =
            new FxAccountCodeParams();

        //�،���ЃR�[�h:GFT�����J�ݏ�Params.�،���ЃR�[�h
        l_fxAccountCodeParams.setInstitutionCode(
            l_gftAccountOpenStatusParams.getInstitutionCode());

        //���X�R�[�h:GFT�����J�ݏ�Params.���X�R�[�h
        l_fxAccountCodeParams.setBranchCode(
            l_gftAccountOpenStatusParams.getBranchCode());

        //FX�V�X�e���R�[�h
        //<����.FX�������.�R�[�X�敪==�h1�hor�h2�h�̏ꍇ>
        //GFT�����J�ݏ�Params.FX�V�X�e���R�[�h
        //<����.FX�������.GFT�R�[�X�敪==�h3�h�̏ꍇ>
        //<����.�����J��FX�V�X�e���R�[�h==null�̏ꍇ>
        //GFT�����J�ݏ�Params.FX�V�X�e���R�[�h
        //<����.�����J��FX�V�X�e���R�[�h��null�łȂ��ꍇ>
        //����.�����J��FX�V�X�e���R�[�h
        if (WEB3GftTransStatusCourseDivDef.ONE_COSE.equals(l_fxAccInformation.fxCourseDiv)
            || WEB3GftTransStatusCourseDivDef.TEN_COSE.equals(l_fxAccInformation.fxCourseDiv))
        {
            l_fxAccountCodeParams.setFxSystemCode(
                l_gftAccountOpenStatusParams.getFxSystemCode());
        }
        else if (WEB3GftTransStatusCourseDivDef.CFD_COURSE.equals(l_fxAccInformation.fxCourseDiv))
        {
            if (l_strSimultaneousFxSystemCode == null)
            {
                l_fxAccountCodeParams.setFxSystemCode(
                    l_gftAccountOpenStatusParams.getFxSystemCode());
            }
            else
            {
                l_fxAccountCodeParams.setFxSystemCode(l_strSimultaneousFxSystemCode);
            }
        }

        //�ڋq�R�[�h
        //GFT�����J�ݏ�Params.�ڋq�R�[�h
        l_fxAccountCodeParams.setAccountCode(
            l_gftAccountOpenStatusParams.getAccountCode());

        //FX�R�[�X�敪
        //FX�������.�R�[�X�敪
        l_fxAccountCodeParams.setFxCourseDiv(l_fxAccInformation.fxCourseDiv);

        //FX�����ԍ�
        //FX�������.�����ԍ�
        l_fxAccountCodeParams.setFxAccountCode(l_fxAccInformation.fxAccountCode);

        //�X�V�҃R�[�h
        //����.�X�V�҃R�[�h
        l_fxAccountCodeParams.setLastUpdater(l_strUpdateCode);

        //�쐬���t
        //���ݎ���
        Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
        l_fxAccountCodeParams.setCreatedTimestamp(l_tsSystemTime);

        //�X�V���t
        //���ݎ���
        l_fxAccountCodeParams.setLastUpdatedTimestamp(l_tsSystemTime);

        try
        {
            WEB3DataAccessUtility.insertRow(l_fxAccountCodeParams);
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

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (insertSOAPMessage)<BR>
     * SOAP���b�Z�[�W�ۑ��e�[�u����SOAP���b�Z�[�W��ۑ�����B<BR>
     * <BR>
     * �P�jSOAP���b�Z�[�W�ۑ�Params�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j���������C���X�^���X�ɁA�ȉ��̂Ƃ���Ƀv���p�e�B���Z�b�g����B<BR>
     * <BR>
     * ���XID�F ����.���XID<BR>
     * �ڑ��敪�F ����.�ڑ��敪<BR>
     * ����M�敪�F ����.����M�敪<BR>
     * ���b�Z�[�W�F ����.���b�Z�[�W<BR>
     * �쐬���t�F �V�X�e���^�C���X�^���v<BR>
     * <BR>
     * �R�j�e�[�u���ɃC���T�[�g����B<BR>
     * @@param l_lngBranchId - ���XID
     * @@param l_strConnectDiv - �ڑ��敪
     * @@param l_strSendReceiveDiv - ����M�敪
     * @@param l_strMessage - ���b�Z�[�W
     * 
     */
    
    public void insertSOAPMessage(
            long l_lngBranchId, 
            String l_strConnectDiv, 
            String l_strSendReceiveDiv,
            String l_strMessage)
    {
        String STR_METHOD_NAME = "insertSOAPMessage(long, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
  	  
        if(String.valueOf(l_lngBranchId) != null && l_strConnectDiv != null &&
        		l_strSendReceiveDiv != null && l_strMessage != null)
        {
   
	        //�P�jSOAP���b�Z�[�W�ۑ�Params�C���X�^���X�𐶐�����B
	        SoapMessageParams l_soapMessageParams = new SoapMessageParams();
	        
	        //�Q�j���������C���X�^���X�ɁA�ȉ��̂Ƃ���Ƀv���p�e�B���Z�b�g����B
	        //���XID�F ����.���XID
	        l_soapMessageParams.setBranchId(l_lngBranchId);
	        
	        //�ڑ��敪�F ����.�ڑ��敪
	        l_soapMessageParams.setConnectDiv(l_strConnectDiv);
	        
	        //����M�敪�F ����.����M�敪
	        l_soapMessageParams.setSendReceiveDiv(l_strSendReceiveDiv);
	        
	        //���b�Z�[�W�F ����.���b�Z�[�W
	        l_soapMessageParams.setSoapMessage(l_strMessage);
	        
	        //�쐬���t�F �V�X�e���^�C���X�^���v
	        Timestamp l_tsSystemTimestamp = new Timestamp(new Date().getTime());
	        l_soapMessageParams.setCreatedTimestamp(l_tsSystemTimestamp);
	        
	        try
	        {		
	            //insert SOAP���b�Z�[�W�ۑ�
	            WEB3DataAccessUtility.insertRow(l_soapMessageParams);
	        }
	        catch (DataException l_ex)
	        {
	            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
	        }
        
        }
        
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (get�ϊ�FX���O�C��ID )<BR>
     * <BR>
     * FX���O�C��ID��ϊ����A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�،���Ѓv���t�@@�����X���ȉ��̏����Ō�������B<BR>
     * �@@[��������]<BR>
     * �@@�،���Ђh�c = ����.�،���Ђh�c<BR>
     * �@@�v���t�@@�����X�� = fx.fxloginid.change.div<BR>
     * �@@�v���t�@@�����X���̘A�� = 1<BR>
     * <BR>
     * �Q�j�@@FX���O�C��ID�̕ϊ����s���B<BR>
     * �Q�|�P�j�@@�،���Ѓv���t�@@�����XParams.�v���t�@@�����X�̒l = ����.FX�V�X�e���R�[�h�̏ꍇ<BR>
     * <BR>
     * ����.FX���O�C��ID������ + ����.FX���O�C��ID(*)�𕶎���A�������l��ԋp����B<BR>
     * <BR>
     * (*)FX���O�C��ID�̐擪����6���ڂ܂ł��g�p����B<BR>
     * <BR>
     * �Q�|�Q�j ��L�ȊO�̏ꍇ���،���Ѓv���t�@@�����X���擾�o���Ȃ������ꍇ���܂�<BR>
     * <BR>
     * ����.FX���O�C��ID��ԋp����B<BR>
     * <BR>
     * @@param l_lngInstitutionID - (�،����ID)<BR>
     * �،����ID<BR>
     * @@param l_strFxSystemCode - (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h<BR>
     * @@param l_strFXLoginFirstChar - (FX���O�C��ID������)<BR>
     * FX���O�C��ID������<BR>
     * @@param l_lngFXLoginID - (FX���O�C���h�c)<BR>
     * FX���O�C���h�c<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getChangedFXLoginID(
        long l_lngInstitutionID,
        String l_strFxSystemCode,
        String l_strFXLoginFirstChar,
        long l_lngFXLoginID) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangedFXLoginID(long, String, String, long)";
        log.entering(STR_METHOD_NAME);

        //�،���Ѓv���t�@@�����X���ȉ��̏����Ō�������B
        //[��������]
        //�،���Ђh�c = ����.�،���Ђh�c
        //�v���t�@@�����X�� = fx.fxloginid.change.div
        //�v���t�@@�����X���̘A�� = 1
        InstitutionPreferencesRow l_institutionPreferencesRow = null;
        try
        {
            l_institutionPreferencesRow = InstitutionPreferencesDao.findRowByPk(
                l_lngInstitutionID,
                WEB3InstitutionPreferencesNameDef.FX_FXLOGINID_CHANGE_DIV,
                1);
        }
        catch (DataFindException l_ex)
        {
            //��L�ȊO�̏ꍇ���،���Ѓv���t�@@�����X���擾�o���Ȃ������ꍇ���܂�
            //����.FX���O�C��ID��ԋp����B
            log.debug("�،���Ѓv���t�@@�����X���擾�o���Ȃ������ꍇ");

            log.exiting(STR_METHOD_NAME);
            return l_lngFXLoginID + "";
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

        //FX���O�C��ID�̕ϊ����s���B
        //�،���Ѓv���t�@@�����XParams.�v���t�@@�����X�̒l = ����.FX�V�X�e���R�[�h�̏ꍇ
        //����.FX���O�C��ID������ + ����.FX���O�C��ID(*)�𕶎���A�������l��ԋp����B
        //(*)FX���O�C��ID�̐擪����6���ڂ܂ł��g�p����B
        if (WEB3Toolkit.isEquals(l_institutionPreferencesRow.getValue(), l_strFxSystemCode))
        {
            String l_strChangedFXLoginID = "";
            if (l_strFXLoginFirstChar != null)
            {
                l_strChangedFXLoginID = l_strFXLoginFirstChar;
            }

            String l_strFXLoginID = l_lngFXLoginID + "";
            if(l_strFXLoginID.length() > 6)
            {
                l_strFXLoginID = l_strFXLoginID.substring(0, 6);
            }

            l_strChangedFXLoginID = l_strChangedFXLoginID + l_strFXLoginID;

            log.exiting(STR_METHOD_NAME);
            return l_strChangedFXLoginID;
        }
        //��L�ȊO�̏ꍇ
        //����.FX���O�C��ID��ԋp����B
        else
        {
            log.exiting(STR_METHOD_NAME);
            return l_lngFXLoginID + "";
        }
    }
}@


1.2
log
@*** empty log message ***
@
text
@d69 1
d96 2
d192 2
a6116 4
     * �@@�@@�i3�jkey   : "weblogic.webservice.transport.https.proxy.host" <BR>
     * �@@�@@ �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f <BR>
     * �@@�@@�i4�jkey   : "weblogic.webservice.transport.https.proxy.port" <BR>
     * �@@�@@ �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f <BR>
a6122 4
     * �@@�@@�i3�jkey   : "weblogic.webservice.transport.http.proxy.host" <BR>
     * �@@�@@ �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f <BR>
     * �@@�@@�i4�jkey   : "weblogic.webservice.transport.http.proxy.port" <BR>
     * �@@�@@ �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f <BR>
d6172 37
a6208 170
//      String l_logMessage = "";
//      
//      
//      //1�j����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g���𕪊����A
//      //�ڑ�������擾����B
//      //getEndpointName().split(arg0 : String)
//      //[����]
//      //arg0�F ";"
//      String[] l_strEndpointNames = l_rpcParams.getEndpointName().split(";");
//
//      //2�j�v���L�V�ݒ���s���B
//      //���O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g�̕����� == 3�̏ꍇ��
//      if (l_strEndpointNames.length == 3)
//      {
//          // ���������f�[�^��3���ڂ̕������"https"���܂܂��ꍇ����
//          //  ���L�̒ʂ�v���p�e�B�Z�b�g���s���B
//          // �i1�jkey   : "https.proxyHost"
//          // �@@�@@ value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f
//          // �i2�jkey   : "https.proxyPort"
//          //  �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f
//          // �i3�jkey   : "weblogic.webservice.transport.https.proxy.host"
//          //  �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f
//          // �i4�jkey   : "weblogic.webservice.transport.https.proxy.port"
//          //  �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f
//          // ������L�̏����ȊO����
//          //  ���L�̒ʂ�v���p�e�B�Z�b�g���s���B
//          // �i1�jkey   : "http.proxyHost"
//          //  �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f
//          // �i2�jkey   : "http.proxyPort"
//          //  �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f
//          // �i3�jkey   : "weblogic.webservice.transport.http.proxy.host"
//          //  �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f
//          // �i4�jkey   : "weblogic.webservice.transport.http.proxy.port"
//          //  �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f
//	      if(l_strEndpointNames[2].trim().indexOf("https") >= 0)
//	      {
//	          System.setProperty("https.proxyHost", l_strEndpointNames[0].trim());
//	          System.setProperty("https.proxyPort", l_strEndpointNames[1].trim());
//	      }
//	      else
//	      {
//	          System.setProperty("http.proxyHost", l_strEndpointNames[0].trim());
//	          System.setProperty("http.proxyPort", l_strEndpointNames[1].trim());
//	      }
//
//      
//      }
//      //���O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g�̕����� == 1�̏ꍇ��
//      else if (l_strEndpointNames.length == 1)
//      {
//      }
//      //����L�̏����ȊO��
//      //��O���X���[����B
//      else
//      {
//          l_logMessage = 
//              "�O���V�X�e��SOAP�ڑ��v���t�@@�����X(RPC�`��).�G���h�|�C���g��" + 
//              "�̃Z�N�V���������A�قȂ��Ă��܂��B\n" +
//              "�uproxy-host;proxy-port;protocol�v�ŃZ�b�g���Ă��������B";
//          //�O���V�X�e���ڑ��G���[
//          throw new WEB3BusinessLayerException(
//              WEB3ErrorCatalog.BUSINESS_ERROR_02398,
//              this.getClass().getName() + "." + STR_METHOD_NAME,
//              l_logMessage
//              );
//      }
//
//      //3�jSSLAdapterFactory�𐶐����A�N���C�A���g�ؖ������Z�b�g����B
//      //�i1�j����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�|�[�g�^�C�v���𕪊����A
//      //�ؖ����t�@@�C���p�X���擾����B
//      //getPortTypeName().split(arg0 : String)
//      //[����]
//      //arg0�F ";"
//      String[] l_strPortTypeNames = l_rpcParams.getPortTypeName().split(";");
//
//      //���O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�|�[�g�^�C�v���̕����� == 2�ȊO�̏ꍇ�A
//      //��O���X���[����B
//      if (l_strPortTypeNames.length != 2)
//      {
//          l_logMessage = 
//              "�O���V�X�e��SOAP�ڑ��v���t�@@�����X(RPC�`��).�|�[�g�^�C�v��" + 
//              "�̃Z�N�V���������A�قȂ��Ă��܂��B\n" +
//              "�u�N���C�A���g�ؖ����̃t���p�X��;CA�ؖ����̃t���p�X���v�ŃZ�b�g���Ă��������B";
//          //�O���V�X�e���ڑ��G���[
//          throw new WEB3BusinessLayerException(
//              WEB3ErrorCatalog.BUSINESS_ERROR_02398,
//              this.getClass().getName() + "." + STR_METHOD_NAME,
//              l_logMessage
//              );
//      }
//
//      //�i2�jSSLAdapterFactory�𐶐�����B
//      //getDefaultFactory();
//      SSLAdapterFactory l_adapterFactory = SSLAdapterFactory.getDefaultFactory();
//
//      //�i3�j�i2�j�Ő�������SSLAdapterFactory����WLSSLAdapter���擾����B
//      //getSSLAdapter()
////      WLSSLAdapter l_adapter = (WLSSLAdapter)l_adapterFactory.getSSLAdapter();
//      WLSSLAdapter l_adapter  = new WLSSLAdapter();
//
//      //�i4�jFileInputStream�C���X�^���X�𐶐����A�N���C�A���g�ؖ����t�@@�C�����̃t���p�X���i�[����B
//      //FileInputStream(arg0 : String);
//      //[����]
//      //arg0�F �i1�j�Ŏ擾�����ؖ����t�@@�C���p�X�̔z���1�v�f
//      try
//      {
//          FileInputStream l_fileInputStream = new FileInputStream(
//              l_strPortTypeNames[0]);
//
//          //�i5�j�i3�j�Ŏ擾����WLSSLAdapter�ɃN���C�A���g�ؖ����y�уL�[���Z�b�g����B
//          //loadLocalIdentity(arg0 : InputStream, arg1 : char[]);
//          //[����]
//          //arg0�F �i4�j�Ő�������FileInputStream
//          //arg1�F �O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�I�y���[�V������.toCharArray()
//          l_adapter.loadLocalIdentity(
//              l_fileInputStream,
//              l_rpcParams.getOperationName().toCharArray());
//
//          //�i6�j�i3�j�Ŏ擾����WLSSLAdapter��CA�ؖ����t�@@�C�����̃t���p�X���i�[����B
//          //setTrustedCertificatesFile(arg0 : String);
//          //[����]
//          //arg0�F �i1�j�Ŏ擾�����ؖ����t�@@�C���p�X�̔z���2�v�f
//          l_adapter.setTrustedCertificatesFile(l_strPortTypeNames[1]);
//      }
//      catch (FileNotFoundException l_ex)
//      {
//          l_logMessage = 
//              "�������N���C�A���g�ؖ������擾�ł��܂���B";
//          //�O���V�X�e���ڑ��G���[
//          throw new WEB3BusinessLayerException(
//              WEB3ErrorCatalog.BUSINESS_ERROR_02398,
//              this.getClass().getName() + "." + STR_METHOD_NAME,
//              l_logMessage
//              );
//      }
//      catch (KeyManagementException l_ex)
//      {
//          l_logMessage = 
//              "�������N���C�A���g�ؖ������擾�ł��܂���B";
//          //�O���V�X�e���ڑ��G���[
//          throw new WEB3BusinessLayerException(
//              WEB3ErrorCatalog.BUSINESS_ERROR_02398,
//              this.getClass().getName() + "." + STR_METHOD_NAME,
//              l_logMessage
//              );
//      }
//      catch (IllegalArgumentException l_ex)
//      {
//          l_logMessage = 
//              "������CA�ؖ������擾�ł��܂���B";
//          //�O���V�X�e���ڑ��G���[
//          throw new WEB3BusinessLayerException(
//              WEB3ErrorCatalog.BUSINESS_ERROR_02398,
//              this.getClass().getName() + "." + STR_METHOD_NAME,
//              l_logMessage
//              );
//      }
//
//      //�i7�j�i2�j�Ő�������SSLAdapterFactory�Ƀf�t�H���g��WLSSLAdapter���Z�b�g����B
//      //setDefaultAdapter(arg0 : SSLAdapter);
//      //[����]
//      //arg0�F �i3�j�Ŏ擾����WLSSLAdapter
//      l_adapterFactory.setDefaultAdapter(l_adapter);
//
//      //�i8�j�i1�j�Ő�������SSLAdapterFactory�Ɂi7�j�ŃZ�b�g����WLSSLAdapter���f�t�H���g�ݒ�ɂ���B
//      //setUseDefaultAdapter(arg0 : boolean);
//      //[����]
//      //arg0�F true
//      l_adapterFactory.setUseDefaultAdapter(true);
		
d6210 70
a6530 2
     * �@@�@@�@@�@@�@@�@@�@@arg0�F "weblogic.webservice.rpc.timeoutsecs"<BR>
     * �@@�@@�@@�@@�@@�@@�@@arg1�F ����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�ڑ��^�C���A�E�g����<BR>
d6658 4
a6661 11
            //_setProperty(arg0 : String, arg1 : Object)
            //[����]
            //arg0�F "weblogic.webservice.rpc.timeoutsecs"
            //arg1�F ����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�ڑ��^�C���A�E�g����
//            l_webServiceSoap_Stub._setProperty(
//                "weblogic.webservice.rpc.timeoutsecs",
//                l_rpcParams.getResponseTimeout());
            //1 minute for connection ((BindingProvider)
            l_bp.getRequestContext().put("com.sun.xml.ws.connect.timeout", l_rpcParams.response_timeout);
            //3 minutos for request ((BindingProvider)
            l_bp.getRequestContext().put("com.sun.xml.ws.request.timeout", l_rpcParams.response_timeout);
@


1.1
log
@*** empty log message ***
@
text
@a68 1
import javax.xml.rpc.ServiceException;
d70 2
a71 2
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.handler.HandlerRegistry;
a72 2
import jp.co.hitachi.www.TFX.WebService.EntryReceiptIn;
import jp.co.hitachi.www.TFX.WebService.EntryReceiptOut;
d81 1
d93 2
a94 2
import com.gftforex.soap.api.ResultInfoCreateUser;
import com.gftforex.soap.api.ResultInfoAddAccount;
d96 2
d99 1
a99 2
import fx.client.WebServiceSoap_Stub;
import fx.client.WebService_Impl;
a173 2
import weblogic.webservice.client.SSLAdapterFactory;
import weblogic.webservice.client.WLSSLAdapter;
d6175 169
a6343 177
      String l_logMessage = "";
      
      
      //1�j����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g���𕪊����A
      //�ڑ�������擾����B
      //getEndpointName().split(arg0 : String)
      //[����]
      //arg0�F ";"
      String[] l_strEndpointNames = l_rpcParams.getEndpointName().split(";");

      //2�j�v���L�V�ݒ���s���B
      //���O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g�̕����� == 3�̏ꍇ��
      if (l_strEndpointNames.length == 3)
      {
          // ���������f�[�^��3���ڂ̕������"https"���܂܂��ꍇ����
          //  ���L�̒ʂ�v���p�e�B�Z�b�g���s���B
          // �i1�jkey   : "https.proxyHost"
          // �@@�@@ value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f
          // �i2�jkey   : "https.proxyPort"
          //  �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f
          // �i3�jkey   : "weblogic.webservice.transport.https.proxy.host"
          //  �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f
          // �i4�jkey   : "weblogic.webservice.transport.https.proxy.port"
          //  �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f
          // ������L�̏����ȊO����
          //  ���L�̒ʂ�v���p�e�B�Z�b�g���s���B
          // �i1�jkey   : "http.proxyHost"
          //  �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f
          // �i2�jkey   : "http.proxyPort"
          //  �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f
          // �i3�jkey   : "weblogic.webservice.transport.http.proxy.host"
          //  �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f
          // �i4�jkey   : "weblogic.webservice.transport.http.proxy.port"
          //  �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f
	      if(l_strEndpointNames[2].trim().indexOf("https") >= 0)
	      {
	          System.setProperty("https.proxyHost", l_strEndpointNames[0].trim());
	          System.setProperty("https.proxyPort", l_strEndpointNames[1].trim());
	          System.setProperty("weblogic.webservice.transport.https.proxy.host",
	              l_strEndpointNames[0].trim());
	          System.setProperty("weblogic.webservice.transport.https.proxy.port",
	              l_strEndpointNames[1].trim());
	      }
	      else
	      {
	          System.setProperty("http.proxyHost", l_strEndpointNames[0].trim());
	          System.setProperty("http.proxyPort", l_strEndpointNames[1].trim());
	          System.setProperty("weblogic.webservice.transport.http.proxy.host",
	              l_strEndpointNames[0].trim());
	          System.setProperty("weblogic.webservice.transport.http.proxy.port",
	              l_strEndpointNames[1].trim());
	      }

      
      }
      //���O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g�̕����� == 1�̏ꍇ��
      else if (l_strEndpointNames.length == 1)
      {
      }
      //����L�̏����ȊO��
      //��O���X���[����B
      else
      {
          l_logMessage = 
              "�O���V�X�e��SOAP�ڑ��v���t�@@�����X(RPC�`��).�G���h�|�C���g��" + 
              "�̃Z�N�V���������A�قȂ��Ă��܂��B\n" +
              "�uproxy-host;proxy-port;protocol�v�ŃZ�b�g���Ă��������B";
          //�O���V�X�e���ڑ��G���[
          throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_02398,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_logMessage
              );
      }

      //3�jSSLAdapterFactory�𐶐����A�N���C�A���g�ؖ������Z�b�g����B
      //�i1�j����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�|�[�g�^�C�v���𕪊����A
      //�ؖ����t�@@�C���p�X���擾����B
      //getPortTypeName().split(arg0 : String)
      //[����]
      //arg0�F ";"
      String[] l_strPortTypeNames = l_rpcParams.getPortTypeName().split(";");

      //���O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�|�[�g�^�C�v���̕����� == 2�ȊO�̏ꍇ�A
      //��O���X���[����B
      if (l_strPortTypeNames.length != 2)
      {
          l_logMessage = 
              "�O���V�X�e��SOAP�ڑ��v���t�@@�����X(RPC�`��).�|�[�g�^�C�v��" + 
              "�̃Z�N�V���������A�قȂ��Ă��܂��B\n" +
              "�u�N���C�A���g�ؖ����̃t���p�X��;CA�ؖ����̃t���p�X���v�ŃZ�b�g���Ă��������B";
          //�O���V�X�e���ڑ��G���[
          throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_02398,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_logMessage
              );
      }

      //�i2�jSSLAdapterFactory�𐶐�����B
      //getDefaultFactory();
      SSLAdapterFactory l_adapterFactory = SSLAdapterFactory.getDefaultFactory();

      //�i3�j�i2�j�Ő�������SSLAdapterFactory����WLSSLAdapter���擾����B
      //getSSLAdapter()
//      WLSSLAdapter l_adapter = (WLSSLAdapter)l_adapterFactory.getSSLAdapter();
      WLSSLAdapter l_adapter  = new WLSSLAdapter();

      //�i4�jFileInputStream�C���X�^���X�𐶐����A�N���C�A���g�ؖ����t�@@�C�����̃t���p�X���i�[����B
      //FileInputStream(arg0 : String);
      //[����]
      //arg0�F �i1�j�Ŏ擾�����ؖ����t�@@�C���p�X�̔z���1�v�f
      try
      {
          FileInputStream l_fileInputStream = new FileInputStream(
              l_strPortTypeNames[0]);

          //�i5�j�i3�j�Ŏ擾����WLSSLAdapter�ɃN���C�A���g�ؖ����y�уL�[���Z�b�g����B
          //loadLocalIdentity(arg0 : InputStream, arg1 : char[]);
          //[����]
          //arg0�F �i4�j�Ő�������FileInputStream
          //arg1�F �O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�I�y���[�V������.toCharArray()
          l_adapter.loadLocalIdentity(
              l_fileInputStream,
              l_rpcParams.getOperationName().toCharArray());

          //�i6�j�i3�j�Ŏ擾����WLSSLAdapter��CA�ؖ����t�@@�C�����̃t���p�X���i�[����B
          //setTrustedCertificatesFile(arg0 : String);
          //[����]
          //arg0�F �i1�j�Ŏ擾�����ؖ����t�@@�C���p�X�̔z���2�v�f
          l_adapter.setTrustedCertificatesFile(l_strPortTypeNames[1]);
      }
      catch (FileNotFoundException l_ex)
      {
          l_logMessage = 
              "�������N���C�A���g�ؖ������擾�ł��܂���B";
          //�O���V�X�e���ڑ��G���[
          throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_02398,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_logMessage
              );
      }
      catch (KeyManagementException l_ex)
      {
          l_logMessage = 
              "�������N���C�A���g�ؖ������擾�ł��܂���B";
          //�O���V�X�e���ڑ��G���[
          throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_02398,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_logMessage
              );
      }
      catch (IllegalArgumentException l_ex)
      {
          l_logMessage = 
              "������CA�ؖ������擾�ł��܂���B";
          //�O���V�X�e���ڑ��G���[
          throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_02398,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_logMessage
              );
      }

      //�i7�j�i2�j�Ő�������SSLAdapterFactory�Ƀf�t�H���g��WLSSLAdapter���Z�b�g����B
      //setDefaultAdapter(arg0 : SSLAdapter);
      //[����]
      //arg0�F �i3�j�Ŏ擾����WLSSLAdapter
      l_adapterFactory.setDefaultAdapter(l_adapter);

      //�i8�j�i1�j�Ő�������SSLAdapterFactory�Ɂi7�j�ŃZ�b�g����WLSSLAdapter���f�t�H���g�ݒ�ɂ���B
      //setUseDefaultAdapter(arg0 : boolean);
      //[����]
      //arg0�F true
      l_adapterFactory.setUseDefaultAdapter(true);
d6687 1
a6687 1
        WebServiceSoap_Stub l_webServiceSoap_Stub = null;
a6688 2
        try
        {
d6691 1
a6691 1
            WebService service = new WebService_Impl();
d6698 1
a6698 1
                (WebServiceSoap_Stub)service.getWebServiceSoap(
d6721 3
a6723 3
            l_webServiceSoap_Stub._setProperty(
                "javax.xml.rpc.service.endpoint.address",
                l_strProtocolStr);
d6730 7
a6736 3
            l_webServiceSoap_Stub._setProperty(
                "weblogic.webservice.rpc.timeoutsecs",
                l_rpcParams.getResponseTimeout());
d6745 1
a6745 1
                EntryReceiptIn l_entryReceiptIn = new EntryReceiptIn();
d6778 2
d6781 5
a6785 20
                //(A) Web �T�[�r�X�|�[�g�̏C�������i�[����I�u�W�F�N�g���쐬
                QName portName = new QName( l_rpcParams.target_namespace_name, l_rpcParams.service_name);

                //(B) HandlerRegistry �I�u�W�F�N�g���쐬
                HandlerRegistry registry = service.getHandlerRegistry();
                
                //(C) HandlerInfo�ɐݒ肷��config(:Map)�f�[�^�̍쐬
                HashMap map = new HashMap();
                
                String branch_id_str = String.valueOf(l_rpcParams.branch_id);
                
                map.put("BranchId",branch_id_str);
                map.put("ConnectDiv",l_rpcParams.connect_div);

                //(D) SOAP���b�Z�[�W�̃n���h�� �`�F�[��(:ArrayList)����
                List handlerList = new ArrayList();
                handlerList.add( new HandlerInfo( WEB3FXSOAPMsgHandler.class, map, null ) );

                //(E) �n���h�� �`�F�[����o�^ //
                registry.setHandlerChain( portName, handlerList ); 
a6796 46
        }
        catch (RemoteException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);

            //�ڑ��^�C���A�E�g�G���[
            if (l_ex.getCause() instanceof SocketTimeoutException)
            {
                // �O���V�X�e���ڑ��G���[
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02398,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            // �O���V�X�e���ڑ��G���[
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (ServiceException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            // �v���I�ȃV�X�e���G���[
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (IOException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            // �v���I�ȃV�X�e���G���[
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
d7250 1
a7250 1
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoCreateUser.getAccountIds()[0] + "";
d7263 1
a7263 1
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoCreateUser.getAccountIds()[1] + "";
d7276 1
a7276 1
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoCreateUser.getAccountIds()[2] + "";
d7294 1
a7294 1
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoCreateUser.getAccountIds()[0] + "";
d7307 1
a7307 1
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoCreateUser.getAccountIds()[1] + "";
d7325 1
a7325 1
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoCreateUser.getAccountIds()[0] + "";
d7354 1
a7354 1
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoAddAccount.getAccountId()[0] + "";
d7367 1
a7367 1
                    l_fxAccInformationUnit.fxAccountCode = l_resultInfoAddAccount.getAccountId()[1] + "";
d7386 1
a7386 1
                	l_fxAccInformationUnit.fxAccountCode = l_resultInfoAddAccount.getAccountId()[0] + "";
@

