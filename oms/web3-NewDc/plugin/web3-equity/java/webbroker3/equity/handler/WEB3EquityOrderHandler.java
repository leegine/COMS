head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������n���h��(WEB3EquityOrderHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/18 �^��(���u) �V�K�쐬
                   2004/12/20 �������F(SRA) �c�Č��Ή��ɂ��C��
                   2005/01/06 �����a��(SRA) JavaDoc�C��
Revesion History : 2007/12/17 �����F ���f��1206
Revesion History : 2008/01/08 �����F ���f��1280
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.equity.message.WEB3EquityBuyCompleteRequest;
import webbroker3.equity.message.WEB3EquityBuyCompleteResponse;
import webbroker3.equity.message.WEB3EquityBuyConfirmRequest;
import webbroker3.equity.message.WEB3EquityBuyConfirmResponse;
import webbroker3.equity.message.WEB3EquitySellCompleteRequest;
import webbroker3.equity.message.WEB3EquitySellCompleteResponse;
import webbroker3.equity.message.WEB3EquitySellConfirmRequest;
import webbroker3.equity.message.WEB3EquitySellConfirmResponse;
import webbroker3.equity.service.delegate.WEB3EquityOrderService;
import webbroker3.equity.service.delegate.WEB3EquityPTSOrderService;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.util.WEB3LogUtility;

/**
 * �i���������n���h���j�B<BR>
 * <BR>
 * ���������n���h���N���X
 * @@version 1.0
 * @@see com.fitechlabs.xtrade.kernel.handler.MessageHandler
 */
public class WEB3EquityOrderHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderHandler.class);

    /**
     * �f�t�H���g�R���X�g���N�^�B<BR>
     * <BR>
     * @@roseuid 4034965402D6
     */
    public WEB3EquityOrderHandler()
    {

    }

    /**
     * (�����������t�m�F���N�G�X�g)<BR>
     * �N���C�A���g��胊�N�G�X�g���󂯁A�������������m�F���������s����B <BR>
     * �i�V�X�e���������j�K�C�h 4.5.�n���h���@@�Q�Ɓj <BR>
     * <BR>
     * �P�j�s��R�[�h�̃`�F�b�N���s���B <BR>
     * <BR>
     * �@@�P�|�P�j���N�G�X�g.�s��R�[�h �� null�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�s��R�[�h��null�v�̗�O���X���[����B  <BR>
     * <BR>
     * �@@�P�|�Q�j���N�G�X�g.�s��R�[�h��null���� <BR>
     * �@@�@@�@@�@@�@@���L�̒l�ȊO�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B  <BR>
     * �@@�@@�@@�@@�@@�E�P�F����  <BR>
     * �@@�@@�@@�@@�@@�E�Q�F���  <BR>
     * �@@�@@�@@�@@�@@�E�R�F���É�  <BR>
     * �@@�@@�@@�@@�@@�E�U�F����  <BR>
     * �@@�@@�@@�@@�@@�E�W�F�D�y  <BR>
     * �@@�@@�@@�@@�@@�E�X�FNNM  <BR>
     * �@@�@@�@@�@@�@@�E�P�O�FJASDAQ  <BR>
     * �@@�@@�@@�@@�@@�E�P�P�FJNX-PTS  <BR>
     * �@@�@@�@@�@@�@@�E�X�X�F�D��s��  <BR>
     * <BR>
     * �Q�j���N�G�X�g.�s��R�[�h != "�D��s��"�̏ꍇ�A�s��I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �@@�@@[�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()�ɃZ�b�g�������]  <BR>
     * <BR>
     * �@@�@@�،���ЁFOpLoginSecurityService���擾�����،���ЃR�[�h <BR>
     * �@@�@@�s��R�[�h�F���N�G�X�g.�s��R�[�h <BR>
     * <BR>
     * �R�jPTS�s�ꂩ�ǂ������肵�������Ăѕ�����B <BR>
     * <BR>
     * �@@�@@�R�|�P�j���N�G�X�g.�s��R�[�h != "�D��s��"�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@���APTS�s��̏ꍇ(�s��.isPTS�s��()==true) <BR>
     * �@@�@@�@@�@@�@@�@@(PTS)�������������T�[�r�X�I�u�W�F�N�g���擾���Aexecute()���R�[������B <BR>
     * <BR>
     * �@@�@@�R�|�Q�j���N�G�X�g.�s��R�[�h == "�D��s��"�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@�܂��́APTS�s��łȂ��ꍇ(�s��.isPTS�s��()==false) <BR>
     * �@@�@@�@@�@@�@@�@@���������T�[�r�X�I�u�W�F�N�g���擾���Aexecute()���R�[������B <BR>
     * <BR>
     * �S�j�T�[�r�X�ŗ�O�����������ꍇ�́A<BR>
     * �G���[�������X�|���X���b�Z�[�W�ɐݒ肷��B <BR>
     * <BR>
     * �T�j���X�|���X�I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_request
     * @@return WEB3EquityBuyOrderConfirmResponse
     * @@roseuid 4042EF0E0303
     */
    public WEB3EquityBuyConfirmResponse equityBuyOrderConfirm(WEB3EquityBuyConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "equityBuyOrderConfirm(WEB3EquityBuyConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityBuyConfirmResponse l_response = null;

        //�P�j�s��R�[�h�̃`�F�b�N���s���B
        //�P�|�P�j���N�G�X�g.�s��R�[�h �� null�̏ꍇ�A
        //�u�s��R�[�h��null�v�̗�O���X���[����B
        if (l_request.marketCode == null)
        {
            l_response =
                (WEB3EquityBuyConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_00443;
            WEB3BusinessLayerException l_exp =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                    STR_METHOD_NAME);
            log.error("�s��R�[�h�����w��ł��B", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�P�|�Q�j���N�G�X�g.�s��R�[�h��null���� ���L�̒l�ȊO�̏ꍇ�A
        //�u�s��R�[�h������`�̒l�v�̗�O���X���[����B
        //�E�P�F����
        //�E�Q�F���
        //�E�R�F���É�
        //�E�U�F����
        //�E�W�F�D�y
        //�E�X�FNNM
        //�E�P�O�FJASDAQ
        //�E�P�P�FJNX-PTS
        //�E�X�X�F�D��s��
        if (!(WEB3MarketCodeDef.TOKYO.equals(l_request.marketCode)
            || WEB3MarketCodeDef.OSAKA.equals(l_request.marketCode)
            || WEB3MarketCodeDef.NAGOYA.equals(l_request.marketCode)
            || WEB3MarketCodeDef.FUKUOKA.equals(l_request.marketCode)
            || WEB3MarketCodeDef.SAPPORO.equals(l_request.marketCode)
            || WEB3MarketCodeDef.NNM.equals(l_request.marketCode)
            || WEB3MarketCodeDef.JASDAQ.equals(l_request.marketCode)
            || WEB3MarketCodeDef.JNX_PTS.equals(l_request.marketCode)
            || WEB3MarketCodeDef.PRIORITY_MARKET.equals(l_request.marketCode)))
        {
            l_response =
                (WEB3EquityBuyConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_00608;
            WEB3BusinessLayerException l_exp =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                    STR_METHOD_NAME);
            log.error("�s��R�[�h�����݂��Ȃ��R�[�h�l�ł��B", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�Q�j���N�G�X�g.�s��R�[�h != "�D��s��"�̏ꍇ�A�s��I�u�W�F�N�g���擾����B
        //[�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()�ɃZ�b�g�������]
        //�،���ЁFOpLoginSecurityService���擾�����،���ЃR�[�h
        //�s��R�[�h�F���N�G�X�g.�s��R�[�h
        WEB3GentradeMarket l_market = null;
        boolean l_blnIsPTSMarket = false;
        if (!WEB3MarketCodeDef.PRIORITY_MARKET.equals(l_request.marketCode))
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(
                    OpLoginSecurityService.class);
            AccountManager l_accountManager = l_finApp.getAccountManager();
            WEB3GentradeFinObjectManager l_objectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            try
            {
                long l_lngAccountId = l_opLoginSec.getAccountId();
                MainAccount l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
                //�،���ЃR�[�h
                String l_strInstitutionCode =
                    l_mainAccount.getInstitution().getInstitutionCode();
                l_market = (WEB3GentradeMarket)l_objectManager.getMarket(
                    l_strInstitutionCode, l_request.marketCode);
            }
            catch (NotFoundException l_ex)
            {
                l_response =
                    (WEB3EquityBuyConfirmResponse)l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }

            //�s��.isPTS�s��()
            try
            {
                l_blnIsPTSMarket = l_market.isPTSMarket();
            }
            catch (WEB3BaseException l_ex)
            {
                l_response =
                    (WEB3EquityBuyConfirmResponse)l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80003;
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
        }

        //�R�jPTS�s�ꂩ�ǂ������肵�������Ăѕ�����B
        //�R�|�P�j���N�G�X�g.�s��R�[�h != "�D��s��"�̏ꍇ�A
        //�@@�@@�@@�@@���APTS�s��̏ꍇ(�s��.isPTS�s��()==true)
        //�@@�@@�@@�@@(PTS)�������������T�[�r�X�I�u�W�F�N�g���擾���Aexecute()���R�[������B
        if (!WEB3MarketCodeDef.PRIORITY_MARKET.equals(l_request.marketCode) && l_blnIsPTSMarket)
        {
            WEB3EquityPTSOrderService l_service = null;
            try
            {
                l_service =
                    (WEB3EquityPTSOrderService)Services.getService(
                        WEB3EquityPTSOrderService.class);
            }
            // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            catch (Exception l_ex)
            {
                l_response =
                    (WEB3EquityBuyConfirmResponse)l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error(
                    l_request,
                    "(PTS)�������������T�[�r�X�I�u�W�F�N�g�擾�Ɏ��s���܂����B",
                    l_response.errorInfo,
                    l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }

            try
            {
                l_response =
                    (WEB3EquityBuyConfirmResponse)l_service.execute(l_request);
            }
            catch (WEB3BaseException l_ex)
            {
                l_response =
                    (WEB3EquityBuyConfirmResponse)l_request.createResponse();
                l_response.errorInfo = l_ex.getErrorInfo();
                l_response.errorMessage = l_ex.getErrorMessage();
                log.error(l_request, "(PTS)�������t�����m�F�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            catch (WEB3BaseRuntimeException l_ex)
            {
                l_response =
                    (WEB3EquityBuyConfirmResponse)l_request.createResponse();
                l_response.errorInfo = l_ex.getErrorInfo();
                log.error(l_request, "(PTS)�������t�����m�F�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
        }
        //�R�|�Q�j���N�G�X�g.�s��R�[�h == "�D��s��"�̏ꍇ�A
        //�܂��́APTS�s��łȂ��ꍇ(�s��.isPTS�s��()==false)
        //���������T�[�r�X�I�u�W�F�N�g���擾���Aexecute()���R�[������B
        else
        {
            WEB3EquityOrderService l_service = null;        
            try
            {
                l_service =
                    (WEB3EquityOrderService)Services.getService(
                        WEB3EquityOrderService.class);
            }
            catch (Exception e)
            {
                l_response =
                    (WEB3EquityBuyConfirmResponse) l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error(l_request, "�������t�����T�[�r�X�擾�Ɏ��s���܂����B", l_response.errorInfo, e);
            }
    
            try
            {
                l_response =
                    (WEB3EquityBuyConfirmResponse)l_service.execute(l_request);
            }
            catch (WEB3BaseException e)
            {
                l_response =
                    (WEB3EquityBuyConfirmResponse)l_request.createResponse();
                l_response.errorInfo = e.getErrorInfo();
                l_response.errorMessage = e.getErrorMessage();
                log.error(l_request, "�������t�����m�F�Ɏ��s���܂����B", e.getErrorInfo(), e);
            }
            catch (WEB3BaseRuntimeException l_bre)
            {
                l_response =
                    (WEB3EquityBuyConfirmResponse)l_request.createResponse();
                l_response.errorInfo = l_bre.getErrorInfo();
                log.error(l_request, "�������t�����m�F�Ɏ��s���܂����B", l_bre.getErrorInfo(), l_bre);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (�����������t�������N�G�X�g)<BR>
     * �N���C�A���g��胊�N�G�X�g���󂯁A�������������������������s����B <BR>
     * �i�V�X�e���������j�K�C�h 4.5.�n���h���@@�Q�Ɓj <BR>
     * <BR>
     * �P�j�s��R�[�h�̃`�F�b�N���s���B <BR>
     * <BR>
     * �@@�P�|�P�j���N�G�X�g.�s��R�[�h �� null�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�s��R�[�h��null�v�̗�O���X���[����B  <BR>
     * <BR>
     * �@@�P�|�Q�j���N�G�X�g.�s��R�[�h��null���� <BR>
     * �@@�@@�@@�@@�@@���L�̒l�ȊO�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B  <BR>
     * �@@�@@�@@�@@�@@�E�P�F����  <BR>
     * �@@�@@�@@�@@�@@�E�Q�F���  <BR>
     * �@@�@@�@@�@@�@@�E�R�F���É�  <BR>
     * �@@�@@�@@�@@�@@�E�U�F����  <BR>
     * �@@�@@�@@�@@�@@�E�W�F�D�y  <BR>
     * �@@�@@�@@�@@�@@�E�X�FNNM  <BR>
     * �@@�@@�@@�@@�@@�E�P�O�FJASDAQ  <BR>
     * �@@�@@�@@�@@�@@�E�P�P�FJNX-PTS  <BR>
     * �@@�@@�@@�@@�@@�E�X�X�F�D��s��  <BR>
     * <BR>
     * �Q�j���N�G�X�g.�s��R�[�h != "�D��s��"�̏ꍇ�A�s��I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �@@�@@[�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()�ɃZ�b�g�������]  <BR>
     * <BR>
     * �@@�@@�،���ЁFOpLoginSecurityService���擾�����،���ЃR�[�h <BR>
     * �@@�@@�s��R�[�h�F���N�G�X�g.�s��R�[�h <BR>
     * <BR>
     * �R�jPTS�s�ꂩ�ǂ������肵�������Ăѕ�����B <BR>
     * <BR>
     * �@@�@@�R�|�P�j���N�G�X�g.�s��R�[�h != "�D��s��"�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@���APTS�s��̏ꍇ(�s��.isPTS�s��()==true) <BR>
     * �@@�@@�@@�@@�@@�@@(PTS)�������������T�[�r�X�I�u�W�F�N�g���擾���Aexecute()���R�[������B <BR>
     * <BR>
     * �@@�@@�R�|�Q�j���N�G�X�g.�s��R�[�h == "�D��s��"�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@�܂��́APTS�s��łȂ��ꍇ(�s��.isPTS�s��()==false) <BR>
     * �@@�@@�@@�@@�@@�@@���������T�[�r�X�I�u�W�F�N�g���擾���Aexecute()���R�[������B<BR>
     * <BR>
     * �S�j�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��B<BR>
     * <BR>
     * �T�j���X�|���X�I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_request
     * @@return WEB3EquityBuyOrderCompleteResponse
     * @@roseuid 4042EF0E0322
     */
    public WEB3EquityBuyCompleteResponse equityBuyOrderComplete(WEB3EquityBuyCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "equityBuyOrderComplete(WEB3EquityBuyCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityBuyCompleteResponse l_response = null;

        //�P�j�s��R�[�h�̃`�F�b�N���s���B
        //�P�|�P�j���N�G�X�g.�s��R�[�h �� null�̏ꍇ�A
        //�u�s��R�[�h��null�v�̗�O���X���[����B
        if (l_request.marketCode == null)
        {
            l_response =
                (WEB3EquityBuyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_00443;
            WEB3BusinessLayerException l_exp =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                    STR_METHOD_NAME);
            log.error("�s��R�[�h�����w��ł��B", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�P�|�Q�j���N�G�X�g.�s��R�[�h��null���� ���L�̒l�ȊO�̏ꍇ�A
        //�u�s��R�[�h������`�̒l�v�̗�O���X���[����B
        //�E�P�F����
        //�E�Q�F���
        //�E�R�F���É�
        //�E�U�F����
        //�E�W�F�D�y
        //�E�X�FNNM
        //�E�P�O�FJASDAQ
        //�E�P�P�FJNX-PTS
        //�E�X�X�F�D��s��
        if (!(WEB3MarketCodeDef.TOKYO.equals(l_request.marketCode)
            || WEB3MarketCodeDef.OSAKA.equals(l_request.marketCode)
            || WEB3MarketCodeDef.NAGOYA.equals(l_request.marketCode)
            || WEB3MarketCodeDef.FUKUOKA.equals(l_request.marketCode)
            || WEB3MarketCodeDef.SAPPORO.equals(l_request.marketCode)
            || WEB3MarketCodeDef.NNM.equals(l_request.marketCode)
            || WEB3MarketCodeDef.JASDAQ.equals(l_request.marketCode)
            || WEB3MarketCodeDef.JNX_PTS.equals(l_request.marketCode)
            || WEB3MarketCodeDef.PRIORITY_MARKET.equals(l_request.marketCode)))
        {
            l_response =
                (WEB3EquityBuyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_00608;
            WEB3BusinessLayerException l_exp =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                    STR_METHOD_NAME);
            log.error("�s��R�[�h�����݂��Ȃ��R�[�h�l�ł��B", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�Q�j���N�G�X�g.�s��R�[�h != "�D��s��"�̏ꍇ�A�s��I�u�W�F�N�g���擾����B
        //[�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()�ɃZ�b�g�������]
        //�،���ЁFOpLoginSecurityService���擾�����،���ЃR�[�h
        //�s��R�[�h�F���N�G�X�g.�s��R�[�h
        WEB3GentradeMarket l_market = null;
        boolean l_blnIsPTSMarket = false;
        if (!WEB3MarketCodeDef.PRIORITY_MARKET.equals(l_request.marketCode))
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(
                    OpLoginSecurityService.class);
            AccountManager l_accountManager = l_finApp.getAccountManager();
            WEB3GentradeFinObjectManager l_objectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            try
            {
                long l_lngAccountId = l_opLoginSec.getAccountId();
                MainAccount l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
                //�،���ЃR�[�h
                String l_strInstitutionCode =
                    l_mainAccount.getInstitution().getInstitutionCode();
                l_market = (WEB3GentradeMarket)l_objectManager.getMarket(
                    l_strInstitutionCode, l_request.marketCode);
            }
            catch (NotFoundException l_ex)
            {
                l_response =
                    (WEB3EquityBuyCompleteResponse)l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            
            //�s��.isPTS�s��()
            try
            {
                l_blnIsPTSMarket = l_market.isPTSMarket();
            }
            catch (WEB3BaseException l_ex)
            {
                l_response =
                    (WEB3EquityBuyCompleteResponse)l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80003;
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
        }

        //�R�jPTS�s�ꂩ�ǂ������肵�������Ăѕ�����B
        //�R�|�P�j���N�G�X�g.�s��R�[�h != "�D��s��"�̏ꍇ�A
        //���APTS�s��̏ꍇ(�s��.isPTS�s��()==true)
        //(PTS)�������������T�[�r�X�I�u�W�F�N�g���擾���Aexecute()���R�[������B
        if (!WEB3MarketCodeDef.PRIORITY_MARKET.equals(l_request.marketCode) && l_blnIsPTSMarket)
        {
            WEB3EquityPTSOrderService l_service = null;
            try
            {
                l_service =
                    (WEB3EquityPTSOrderService)Services.getService(
                        WEB3EquityPTSOrderService.class);
            }
            // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            catch (Exception l_ex)
            {
                l_response =
                    (WEB3EquityBuyCompleteResponse)l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error(
                    l_request,
                    "(PTS)�������������T�[�r�X�I�u�W�F�N�g�擾�Ɏ��s���܂����B",
                    l_response.errorInfo,
                    l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }

            try
            {
                l_response =
                    (WEB3EquityBuyCompleteResponse)l_service.execute(l_request);
            }
            catch (WEB3BaseException l_ex)
            {
                l_response =
                    (WEB3EquityBuyCompleteResponse)l_request.createResponse();
                l_response.errorInfo = l_ex.getErrorInfo();
                l_response.errorMessage = l_ex.getErrorMessage();
                log.error(l_request, "(PTS)�������t���������Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            catch (WEB3BaseRuntimeException l_ex)
            {
                l_response =
                    (WEB3EquityBuyCompleteResponse)l_request.createResponse();
                l_response.errorInfo = l_ex.getErrorInfo();
                log.error(l_request, "(PTS)�������t���������Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
        }
        //�R�|�Q�j���N�G�X�g.�s��R�[�h == "�D��s��"�̏ꍇ�A
        //�܂��́APTS�s��łȂ��ꍇ(�s��.isPTS�s��()==false)
        //���������T�[�r�X�I�u�W�F�N�g���擾���Aexecute()���R�[������B
        else
        {
            WEB3EquityOrderService l_service = null;
            try
            {
                l_service =
                    (WEB3EquityOrderService)Services.getService(
                        WEB3EquityOrderService.class);
            }
            catch (Exception e)
            {
                l_response =
                    (WEB3EquityBuyCompleteResponse)l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error(l_request, "�������t�����T�[�r�X�擾�Ɏ��s���܂����B", l_response.errorInfo, e);
            }
    
            try
            {
                l_response =
                    (WEB3EquityBuyCompleteResponse)l_service.execute(l_request);
            }
            catch (WEB3BaseException e)
            {
                l_response =
                    (WEB3EquityBuyCompleteResponse)l_request.createResponse();
                l_response.errorInfo = e.getErrorInfo();
                l_response.errorMessage = e.getErrorMessage();
                log.error(l_request, "�������t���������Ɏ��s���܂����B", e.getErrorInfo(), e);
            }
            catch (WEB3BaseRuntimeException l_bre)
            {
                l_response =
                    (WEB3EquityBuyCompleteResponse)l_request.createResponse();
                l_response.errorInfo = l_bre.getErrorInfo();
                log.error(l_request, "�������t���������Ɏ��s���܂����B", l_bre.getErrorInfo(), l_bre);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (�����������t�m�F���N�G�X�g)<BR>
     * �N���C�A���g��胊�N�G�X�g���󂯁A�������������m�F���������s����B <BR>
     * �i�V�X�e���������j�K�C�h 4.5.�n���h���@@�Q�Ɓj <BR>
     * <BR>
     * �P�j�s��R�[�h�̃`�F�b�N���s���B <BR>
     * <BR>
     * �@@�P�|�P�j���N�G�X�g.�s��R�[�h �� null�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�s��R�[�h��null�v�̗�O���X���[����B  <BR>
     * <BR>
     * �@@�P�|�Q�j���N�G�X�g.�s��R�[�h��null���� <BR>
     * �@@�@@�@@�@@�@@���L�̒l�ȊO�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B  <BR>
     * �@@�@@�@@�@@�@@�E�P�F����  <BR>
     * �@@�@@�@@�@@�@@�E�Q�F���  <BR>
     * �@@�@@�@@�@@�@@�E�R�F���É�  <BR>
     * �@@�@@�@@�@@�@@�E�U�F����  <BR>
     * �@@�@@�@@�@@�@@�E�W�F�D�y  <BR>
     * �@@�@@�@@�@@�@@�E�X�FNNM  <BR>
     * �@@�@@�@@�@@�@@�E�P�O�FJASDAQ  <BR>
     * �@@�@@�@@�@@�@@�E�P�P�FJNX-PTS  <BR>
     * <BR>
     * �Q�j�s��I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �@@�@@[�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()�ɃZ�b�g�������]  <BR>
     * <BR>
     * �@@�@@�،���ЁFOpLoginSecurityService���擾�����،���ЃR�[�h <BR>
     * �@@�@@�s��R�[�h�F���N�G�X�g.�s��R�[�h <BR>
     * <BR>
     * �R�jPTS�s�ꂩ�ǂ������肵�������Ăѕ�����B <BR>
     * <BR>
     * �@@�@@�R�|�P�jPTS�s��̏ꍇ(�s��.isPTS�s��()==true) <BR>
     * �@@�@@�@@�@@�@@�@@(PTS)�������������T�[�r�X�I�u�W�F�N�g���擾���A<BR>
     * �@@�@@�@@�@@�@@�@@execute()���R�[������B <BR>
     * <BR>
     * �@@�@@�R�|�Q�jPTS�s��łȂ��ꍇ(�s��.isPTS�s��()==false) <BR>
     * �@@�@@�@@�@@�@@�@@���������T�[�r�X�I�u�W�F�N�g���擾���Aexecute()���R�[������B<BR>
     * <BR>
     * �S�j�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��B<BR>
     * <BR>
     * �T�j���X�|���X�I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_request �N���C�A���g����̃��N�G�X�g���b�Z�[�W���w�肷��B
     * @@return WEB3EquitySellOrderConfirmResponse
     * @@roseuid 40A02EAD004E
     */
    public WEB3EquitySellConfirmResponse equitySellOrderConfirm(WEB3EquitySellConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "equitySellOrderConfirm(WEB3EquitySellConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquitySellConfirmResponse l_response = null;

        //�P�j�s��R�[�h�̃`�F�b�N���s���B
        //�P�|�P�j���N�G�X�g.�s��R�[�h �� null�̏ꍇ�A
        //�u�s��R�[�h��null�v�̗�O���X���[����B
        if (l_request.marketCode == null)
        {
            l_response =
                (WEB3EquitySellConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_00443;
            WEB3BusinessLayerException l_exp =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                    STR_METHOD_NAME);
            log.error("�s��R�[�h�����w��ł��B", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�P�|�Q�j���N�G�X�g.�s��R�[�h��null���� ���L�̒l�ȊO�̏ꍇ�A
        //�u�s��R�[�h������`�̒l�v�̗�O���X���[����B
        //�E�P�F����
        //�E�Q�F���
        //�E�R�F���É�
        //�E�U�F����
        //�E�W�F�D�y
        //�E�X�FNNM
        //�E�P�O�FJASDAQ
        //�E�P�P�FJNX-PTS
        if (!(WEB3MarketCodeDef.TOKYO.equals(l_request.marketCode)
            || WEB3MarketCodeDef.OSAKA.equals(l_request.marketCode)
            || WEB3MarketCodeDef.NAGOYA.equals(l_request.marketCode)
            || WEB3MarketCodeDef.FUKUOKA.equals(l_request.marketCode)
            || WEB3MarketCodeDef.SAPPORO.equals(l_request.marketCode)
            || WEB3MarketCodeDef.NNM.equals(l_request.marketCode)
            || WEB3MarketCodeDef.JASDAQ.equals(l_request.marketCode)
            || WEB3MarketCodeDef.JNX_PTS.equals(l_request.marketCode)))
        {
            l_response =
                (WEB3EquitySellConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_00608;
            WEB3BusinessLayerException l_exp =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                    STR_METHOD_NAME);
            log.error("�s��R�[�h�����݂��Ȃ��R�[�h�l�ł��B", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�Q�j�s��I�u�W�F�N�g���擾����B
        //[�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()�ɃZ�b�g�������]
        //�،���ЁFOpLoginSecurityService���擾�����،���ЃR�[�h
        //�s��R�[�h�F���N�G�X�g.�s��R�[�h
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        WEB3GentradeFinObjectManager l_objectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        WEB3GentradeMarket l_market = null;
        try
        {
            long l_lngAccountId = l_opLoginSec.getAccountId();
            MainAccount l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
            //�،���ЃR�[�h
            String l_strInstitutionCode =
                l_mainAccount.getInstitution().getInstitutionCode();
            l_market = (WEB3GentradeMarket)l_objectManager.getMarket(
                l_strInstitutionCode, l_request.marketCode);
        }
        catch (NotFoundException l_ex)
        {
            l_response =
                (WEB3EquitySellConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�R�jPTS�s�ꂩ�ǂ������肵�������Ăѕ�����B
        //�R�|�P�jPTS�s��̏ꍇ(�s��.isPTS�s��()==true)
        //(PTS)�������������T�[�r�X�I�u�W�F�N�g���擾���Aexecute()���R�[������B
        boolean l_blnIsPTSMarket = false;
        try
        {
            l_blnIsPTSMarket = l_market.isPTSMarket();
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3EquitySellConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80003;
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        if (l_blnIsPTSMarket)
        {
            WEB3EquityPTSOrderService l_service = null;
            try
            {
                l_service =
                    (WEB3EquityPTSOrderService)Services.getService(
                        WEB3EquityPTSOrderService.class);
            }
            // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            catch (Exception l_ex)
            {
                l_response =
                    (WEB3EquitySellConfirmResponse)l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error(
                    l_request,
                    "(PTS)�������������T�[�r�X�I�u�W�F�N�g�擾�Ɏ��s���܂����B",
                    l_response.errorInfo,
                    l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }

            try
            {
                l_response =
                    (WEB3EquitySellConfirmResponse)l_service.execute(l_request);
            }
            catch (WEB3BaseException l_ex)
            {
                l_response =
                    (WEB3EquitySellConfirmResponse)l_request.createResponse();
                l_response.errorInfo = l_ex.getErrorInfo();
                l_response.errorMessage = l_ex.getErrorMessage();
                log.error(l_request, "(PTS)�������t�����m�F�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            catch (WEB3BaseRuntimeException l_ex)
            {
                l_response =
                    (WEB3EquitySellConfirmResponse)l_request.createResponse();
                l_response.errorInfo = l_ex.getErrorInfo();
                log.error(l_request, "(PTS)�������t�����m�F�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
        }
        //�R�|�Q�jPTS�s��łȂ��ꍇ(�s��.isPTS�s��()==false)
        //���������T�[�r�X�I�u�W�F�N�g���擾���Aexecute()���R�[������B
        else
        {
            WEB3EquityOrderService l_service = null;
            try
            {
                l_service =
                    (WEB3EquityOrderService)Services.getService(
                        WEB3EquityOrderService.class);
            }
            catch (Exception e)
            {
                l_response =
                    (WEB3EquitySellConfirmResponse)l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error(l_request, "�������t�����T�[�r�X�擾�Ɏ��s���܂����B", l_response.errorInfo, e);
            }
    
            try
            {
                l_response =
                    (WEB3EquitySellConfirmResponse)l_service.execute(l_request);
            }
            catch (WEB3BaseException e)
            {
                l_response =
                    (WEB3EquitySellConfirmResponse)l_request.createResponse();
                l_response.errorInfo = e.getErrorInfo();
                l_response.errorMessage = e.getErrorMessage();
                log.error(l_request, "�������t�����m�F�Ɏ��s���܂����B", e.getErrorInfo(), e);
            }
            catch (WEB3BaseRuntimeException l_bre)
            {
                l_response =
                    (WEB3EquitySellConfirmResponse)l_request.createResponse();
                l_response.errorInfo = l_bre.getErrorInfo();
                log.error(l_request, "�������t�����m�F�Ɏ��s���܂����B", l_bre.getErrorInfo(), l_bre);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (�����������t�������N�G�X�g)<BR>
     * �N���C�A���g��胊�N�G�X�g���󂯁A�������������������������s����B <BR>
     * �i�V�X�e���������j�K�C�h 4.5.�n���h���@@�Q�Ɓj <BR>
     * <BR>
     * �P�j�s��R�[�h�̃`�F�b�N���s���B <BR>
     * <BR>
     * �@@�P�|�P�j���N�G�X�g.�s��R�[�h �� null�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�s��R�[�h��null�v�̗�O���X���[����B  <BR>
     * <BR>
     * �@@�P�|�Q�j���N�G�X�g.�s��R�[�h��null���� <BR>
     * �@@�@@�@@�@@�@@���L�̒l�ȊO�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B  <BR>
     * �@@�@@�@@�@@�@@�E�P�F����  <BR>
     * �@@�@@�@@�@@�@@�E�Q�F���  <BR>
     * �@@�@@�@@�@@�@@�E�R�F���É�  <BR>
     * �@@�@@�@@�@@�@@�E�U�F����  <BR>
     * �@@�@@�@@�@@�@@�E�W�F�D�y  <BR>
     * �@@�@@�@@�@@�@@�E�X�FNNM  <BR>
     * �@@�@@�@@�@@�@@�E�P�O�FJASDAQ  <BR>
     * �@@�@@�@@�@@�@@�E�P�P�FJNX-PTS  <BR>
     * <BR>
     * �Q�j�s��I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �@@�@@[�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()�ɃZ�b�g�������]  <BR>
     * <BR>
     * �@@�@@�،���ЁFOpLoginSecurityService���擾�����،���ЃR�[�h <BR>
     * �@@�@@�s��R�[�h�F���N�G�X�g.�s��R�[�h <BR>
     * <BR>
     * �R�jPTS�s�ꂩ�ǂ������肵�������Ăѕ�����B <BR>
     * <BR>
     * �@@�@@�R�|�P�jPTS�s��̏ꍇ(�s��.isPTS�s��()==true) <BR>
     * �@@�@@�@@�@@�@@�@@(PTS)�������������T�[�r�X�I�u�W�F�N�g���擾���A<BR>
     * �@@�@@�@@�@@�@@�@@execute()���R�[������B <BR>
     * <BR>
     * �@@�@@�R�|�Q�jPTS�s��łȂ��ꍇ(�s��.isPTS�s��()==false) <BR>
     * �@@�@@�@@�@@�@@�@@���������T�[�r�X�I�u�W�F�N�g���擾���Aexecute()���R�[������B<BR>
     * <BR>
     * �S�j�T�[�r�X�ŗ�O�����������ꍇ�́A<BR>
     * �G���[�������X�|���X���b�Z�[�W�ɐݒ肷��B <BR>
     * <BR>
     * �T�j���X�|���X�I�u�W�F�N�g��ԋp����B <BR>
     * @@param l_request
     * @@return WEB3EquitySellOrderCompleteResponse
     * @@roseuid 40A02F06004E
     */
    public WEB3EquitySellCompleteResponse equitySellOrderComplete(WEB3EquitySellCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "equitySellOrderComplete(WEB3EquitySellCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquitySellCompleteResponse l_response = null;

        //�P�j�s��R�[�h�̃`�F�b�N���s���B
        //�P�|�P�j���N�G�X�g.�s��R�[�h �� null�̏ꍇ�A
        //�u�s��R�[�h��null�v�̗�O���X���[����B
        if (l_request.marketCode == null)
        {
            l_response =
                (WEB3EquitySellCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_00443;
            WEB3BusinessLayerException l_exp =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                    STR_METHOD_NAME);
            log.error("�s��R�[�h�����w��ł��B", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�P�|�Q�j���N�G�X�g.�s��R�[�h��null���� ���L�̒l�ȊO�̏ꍇ�A
        //�u�s��R�[�h������`�̒l�v�̗�O���X���[����B
        //�E�P�F����
        //�E�Q�F���
        //�E�R�F���É�
        //�E�U�F����
        //�E�W�F�D�y
        //�E�X�FNNM
        //�E�P�O�FJASDAQ
        //�E�P�P�FJNX-PTS
        if (!(WEB3MarketCodeDef.TOKYO.equals(l_request.marketCode)
            || WEB3MarketCodeDef.OSAKA.equals(l_request.marketCode)
            || WEB3MarketCodeDef.NAGOYA.equals(l_request.marketCode)
            || WEB3MarketCodeDef.FUKUOKA.equals(l_request.marketCode)
            || WEB3MarketCodeDef.SAPPORO.equals(l_request.marketCode)
            || WEB3MarketCodeDef.NNM.equals(l_request.marketCode)
            || WEB3MarketCodeDef.JASDAQ.equals(l_request.marketCode)
            || WEB3MarketCodeDef.JNX_PTS.equals(l_request.marketCode)))
        {
            l_response =
                (WEB3EquitySellCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_00608;
            log.debug("�s��R�[�h�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�Q�j�s��I�u�W�F�N�g���擾����B
        //[�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()�ɃZ�b�g�������]
        //�،���ЁFOpLoginSecurityService���擾�����،���ЃR�[�h
        //�s��R�[�h�F���N�G�X�g.�s��R�[�h
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        WEB3GentradeFinObjectManager l_objectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        WEB3GentradeMarket l_market = null;
        try
        {
            long l_lngAccountId = l_opLoginSec.getAccountId();
            MainAccount l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
            //�،���ЃR�[�h
            String l_strInstitutionCode =
                l_mainAccount.getInstitution().getInstitutionCode();
            l_market = (WEB3GentradeMarket)l_objectManager.getMarket(
                l_strInstitutionCode, l_request.marketCode);
        }
        catch (NotFoundException l_ex)
        {
            l_response =
                (WEB3EquitySellCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
            WEB3BusinessLayerException l_exp =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                    STR_METHOD_NAME);
            log.error("�s��R�[�h�����݂��Ȃ��R�[�h�l�ł��B", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�R�jPTS�s�ꂩ�ǂ������肵�������Ăѕ�����B
        //�R�|�P�jPTS�s��̏ꍇ(�s��.isPTS�s��()==true)
        //(PTS)�������������T�[�r�X�I�u�W�F�N�g���擾���Aexecute()���R�[������B
        boolean l_blnIsPTSMarket = false;
        try
        {
            l_blnIsPTSMarket = l_market.isPTSMarket();
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3EquitySellCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80003;
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        if (l_blnIsPTSMarket)
        {
            WEB3EquityPTSOrderService l_service = null;
            try
            {
                l_service =
                    (WEB3EquityPTSOrderService)Services.getService(
                        WEB3EquityPTSOrderService.class);
            }
            // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            catch (Exception l_ex)
            {
                l_response =
                    (WEB3EquitySellCompleteResponse)l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error(
                    l_request,
                    "(PTS)�������������T�[�r�X�I�u�W�F�N�g�擾�Ɏ��s���܂����B",
                    l_response.errorInfo,
                    l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }

            try
            {
                l_response =
                    (WEB3EquitySellCompleteResponse)l_service.execute(l_request);
            }
            catch (WEB3BaseException l_ex)
            {
                l_response =
                    (WEB3EquitySellCompleteResponse)l_request.createResponse();
                l_response.errorInfo = l_ex.getErrorInfo();
                l_response.errorMessage = l_ex.getErrorMessage();
                log.error(l_request, "(PTS)�������t���������Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            catch (WEB3BaseRuntimeException l_ex)
            {
                l_response =
                    (WEB3EquitySellCompleteResponse)l_request.createResponse();
                l_response.errorInfo = l_ex.getErrorInfo();
                log.error(l_request, "(PTS)�������t���������Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
        }
        //�R�|�Q�jPTS�s��łȂ��ꍇ(�s��.isPTS�s��()==false)
        //���������T�[�r�X�I�u�W�F�N�g���擾���Aexecute()���R�[������B
        else
        {
            WEB3EquityOrderService l_service = null;
            try
            {
                l_service =
                    (WEB3EquityOrderService)Services.getService(
                        WEB3EquityOrderService.class);
            }
            catch (Exception e)
            {
                l_response =
                    (WEB3EquitySellCompleteResponse)l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error(l_request, "�������t�����T�[�r�X�擾�Ɏ��s���܂����B", l_response.errorInfo, e);
            }
    
            try
            {
                l_response =
                    (WEB3EquitySellCompleteResponse)l_service.execute(l_request);
            }
            catch (WEB3BaseException e)
            {
                l_response =
                    (WEB3EquitySellCompleteResponse)l_request.createResponse();
                l_response.errorInfo = e.getErrorInfo();
                l_response.errorMessage = e.getErrorMessage();
                log.error(l_request, "�������t���������Ɏ��s���܂����B", e.getErrorInfo(), e);
            }
            catch (WEB3BaseRuntimeException l_bre)
            {
                l_response =
                    (WEB3EquitySellCompleteResponse)l_request.createResponse();
                l_response.errorInfo = l_bre.getErrorInfo();
                log.error(l_request, "�������t���������Ɏ��s���܂����B", l_bre.getErrorInfo(), l_bre);
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
