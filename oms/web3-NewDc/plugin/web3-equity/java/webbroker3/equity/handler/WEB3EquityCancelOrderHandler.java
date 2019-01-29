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
filename	WEB3EquityCancelOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������������n���h��(WEB3EquityCancelOrderHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/27 ���_�� (���u) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
Revesion History : 2007/12/17 �����F ���f��1204
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.message.WEB3EquityCancelCompleteRequest;
import webbroker3.equity.message.WEB3EquityCancelCompleteResponse;
import webbroker3.equity.message.WEB3EquityCancelConfirmRequest;
import webbroker3.equity.message.WEB3EquityCancelConfirmResponse;
import webbroker3.equity.service.delegate.WEB3EquityCancelOrderService;
import webbroker3.equity.service.delegate.WEB3EquityPTSCancelOrderService;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.util.WEB3LogUtility;

/**
 * �i������������n���h���j�B<BR>
 * <BR>
 * ������������n���h���N���X
 * @@version 1.0
 */
public class WEB3EquityCancelOrderHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityCancelOrderHandler.class);

    /**
     * @@roseuid 40AC7C5103A5
     */
    public WEB3EquityCancelOrderHandler()
    {

    }

    /**
     * (������������m�F���N�G�X�g)�B<BR>
     * �N���C�A���g��胊�N�G�X�g���󂯁A����������������m�F���������s����B<BR>
     * �i�V�X�e���������j�K�C�h 4.5.�n���h���@@�Q�Ɓj <BR>
     * <BR>
     * �P�j�@@ID�`�F�b�N���s���B <BR>
     * <BR>
     * �@@���N�G�X�g�f�[�^.ID �� null�̏ꍇ�A <BR>
     * �@@�uID��null�v�̗�O���X���[����B <BR>
     * �@@�ȊO�A�ȉ��̏��������s����B <BR>
     * <BR>
     * �Q�j�@@����Ώے����P�ʂ��擾���� <BR>
     * <BR>
     * �@@�g�����������}�l�[�W��.getOrderUnits(���N�G�X�g�f�[�^.ID)<BR>
     * �@@�Ŏ擾���������P�ʃI�u�W�F�N�g�̂����A <BR>
     * �@@�ŏ��̗v�f���擾����B <BR>
     * <BR>
     * �R�j�@@�s��I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket()���R�[������B <BR>
     * �@@[.getMarket()�ɃZ�b�g�������] <BR>
     * �@@�@@�s��ID:�P�j�Ŏ擾���������P��.�s��ID <BR>
     * <BR>
     * �S�j�@@�s��.isPTS�s��i�j���APTS�s�ꂩ���肵�A�e�������Ăѕ�����B <BR>
     * <BR>
     * �@@�S�|�P�j�@@PTS�s��łȂ��ꍇ�i�s��.isPTS�s��()==false�j <BR>
     * �@@�@@������������T�[�r�X�I�u�W�F�N�g���擾���A <BR>
     * �@@�@@execute�i�j���R�[������B <BR>
     * <BR>
     * �@@�S�|�Q�j�@@PTS�s��̏ꍇ�i�s��.isPTS�s��()==true�j <BR>
     * �@@�@@(PTS)������������T�[�r�X�I�u�W�F�N�g���擾���A <BR>
     * �@@�@@execute�i�j���R�[������B <BR>
     * <BR>
     * �T�j�@@�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W<BR>
     * �@@�ɐݒ肷�� �B<BR>
     * <BR>
     * �U�j�@@���X�|���X�I�u�W�F�N�g��ԋp����B<BR>
     * @@param  l_request <BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 403097930172
     */
    public WEB3EquityCancelConfirmResponse equityCancelConfirmRequest(WEB3EquityCancelConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "equityCancelConfirmRequest(WEB3EquityCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityCancelConfirmResponse l_response = null;

        //�P�j�@@ID�`�F�b�N���s���B
        //���N�G�X�g�f�[�^.ID �� null�̏ꍇ�A�uID��null�v�̗�O���X���[����B
        if (l_request.id == null)
        {
            l_response =
                (WEB3EquityCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_00600;
            WEB3BusinessLayerException l_exp =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                    STR_METHOD_NAME);
            log.error("����ID�����w��ł��B", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�Q�j�@@����Ώے����P�ʂ��擾����
        //�@@�g�����������}�l�[�W��.getOrderUnits(���N�G�X�g�f�[�^.ID)
        //�Ŏ擾���������P�ʃI�u�W�F�N�g�̂����A
        //�@@�ŏ��̗v�f���擾����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        long l_lngOrderId = Long.parseLong(l_request.id);
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        if (l_orderUnits == null || l_orderUnits.length == 0)
        {
            l_response =
                (WEB3EquityCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
            WEB3BusinessLayerException l_exp =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    STR_METHOD_NAME);
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        OrderUnit l_orderUnit = l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //�R�j�@@�s��I�u�W�F�N�g���擾����B
        //�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket()���R�[������B
        //[.getMarket()�ɃZ�b�g�������]�s��ID:�P�j�Ŏ擾���������P��.�s��ID
        WEB3GentradeFinObjectManager l_objectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        WEB3GentradeMarket l_market = null;
        try
        {
            l_market = (WEB3GentradeMarket)l_objectManager.getMarket(l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_ex)
        {
            l_response =
                (WEB3EquityCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�S�j�@@�s��.isPTS�s��i�j���APTS�s�ꂩ���肵�A�e�������Ăѕ�����B
        //�S�|�P�j�@@PTS�s��łȂ��ꍇ�i�s��.isPTS�s��()==false�j
        //������������T�[�r�X�I�u�W�F�N�g���擾���Aexecute�i�j���R�[������B
        boolean l_blnIsPTSMarket = false;
        try
        {
            l_blnIsPTSMarket = l_market.isPTSMarket();
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3EquityCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80003;
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        if (!l_blnIsPTSMarket)
        {
            WEB3EquityCancelOrderService l_service = null;
            try
            {
                l_service =
                    (WEB3EquityCancelOrderService) Services.getService(
                        WEB3EquityCancelOrderService.class);
            }
            catch (Exception e)
            {
                l_response =
                    (WEB3EquityCancelConfirmResponse) l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error(
                    l_request, "������������T�[�r�X�̎擾�Ɏ��s���܂����B", l_response.errorInfo, e);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            
            
            try
            {
                l_response =
                    (WEB3EquityCancelConfirmResponse) l_service.execute(l_request);
            }
            catch (WEB3BaseException e)
            {
                l_response =
                    (WEB3EquityCancelConfirmResponse) l_request.createResponse();
                l_response.errorInfo = e.getErrorInfo();
                log.error(l_request, "������������m�F�Ɏ��s���܂����B", e);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            catch (WEB3BaseRuntimeException l_bre)
            {
                l_response =
                    (WEB3EquityCancelConfirmResponse) l_request.createResponse();
                l_response.errorInfo = l_bre.getErrorInfo();
                log.error(l_request, "������������m�F�Ɏ��s���܂����B", l_bre);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
        }
        //�S�|�Q�j�@@PTS�s��̏ꍇ�i�s��.isPTS�s��()==true�j
        //(PTS)������������T�[�r�X�I�u�W�F�N�g���擾���Aexecute�i�j���R�[������B
        else
        {
            WEB3EquityPTSCancelOrderService l_service = null;
            try
            {
                l_service =
                    (WEB3EquityPTSCancelOrderService)Services.getService(
                        WEB3EquityPTSCancelOrderService.class);
            }
            // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            catch (Exception l_ex)
            {
                l_response =
                    (WEB3EquityCancelConfirmResponse)l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error(
                    l_request,
                    "(PTS)������������T�[�r�X�̎擾�Ɏ��s���܂����B",
                    l_response.errorInfo,
                    l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }

            try
            {
                l_response =
                    (WEB3EquityCancelConfirmResponse)l_service.execute(l_request);
            }
            catch (WEB3BaseException l_ex)
            {
                l_response =
                    (WEB3EquityCancelConfirmResponse)l_request.createResponse();
                l_response.errorInfo = l_ex.getErrorInfo();
                log.error(l_request, "(PTS)�������������m�F�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            catch (WEB3BaseRuntimeException l_ex)
            {
                l_response =
                    (WEB3EquityCancelConfirmResponse)l_request.createResponse();
                l_response.errorInfo = l_ex.getErrorInfo();
                log.error(l_request, "(PTS)�������������m�F�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (������������������N�G�X�g)�B<BR>
     * �N���C�A���g��胊�N�G�X�g���󂯁A����������������������������s����B <BR>
     * �i�V�X�e���������j�K�C�h 4.5.�n���h���@@�Q�Ɓj <BR>
     * <BR>
     * �P�j�@@ID�`�F�b�N���s���B <BR>
     * <BR>
     * �@@���N�G�X�g�f�[�^.ID �� null�̏ꍇ�A <BR>
     * �@@�uID��null�v�̗�O���X���[����B <BR>
     * �@@�ȊO�A�ȉ��̏��������s����B <BR>
     * <BR>
     * �Q�j�@@����Ώے����P�ʂ��擾���� <BR>
     * <BR>
     * �@@�g�����������}�l�[�W��.getOrderUnits(���N�G�X�g�f�[�^.ID)<BR>
     * �@@�Ŏ擾���������P�ʃI�u�W�F�N�g�̂����A <BR>
     * �@@�ŏ��̗v�f���擾����B <BR>
     * <BR>
     * �R�j�@@�s��I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket()���R�[������B <BR>
     * �@@[.getMarket()�ɃZ�b�g�������] <BR>
     * �@@�@@�s��ID:�P�j�Ŏ擾���������P��.�s��ID <BR>
     * <BR>
     * �S�j�@@�s��.isPTS�s��i�j���APTS�s�ꂩ���肵�A�e�������Ăѕ�����B <BR>
     * <BR>
     * �@@�S�|�P�j�@@PTS�s��łȂ��ꍇ�i�s��.isPTS�s��()==false�j <BR>
     * �@@�@@������������T�[�r�X�I�u�W�F�N�g���擾���A <BR>
     * �@@�@@execute�i�j���R�[������B <BR>
     * <BR>
     * �@@�S�|�Q�j�@@PTS�s��̏ꍇ�i�s��.isPTS�s��()==true�j <BR>
     * �@@�@@(PTS)������������T�[�r�X�I�u�W�F�N�g���擾���A <BR>
     * �@@�@@execute�i�j���R�[������B <BR>
     * <BR>
     * �T�j�@@�T�[�r�X�ŗ�O�����������ꍇ�́A<BR>
     * �G���[�������X�|���X���b�Z�[�W�ɐݒ肷��B  <BR>
     * <BR>
     * �U�j�@@���X�|���X�I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_request �N���C�A���g����̃��N�G�X�g���b�Z�[�W���w�肷��B
     * @@return WEB3EquityCancelCompleteResponse
     * @@roseuid 40A02F06004E
     */
    public WEB3EquityCancelCompleteResponse equityCancelCompleteRequest(WEB3EquityCancelCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "equityCancelComplete(WEB3EquityCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityCancelCompleteResponse l_response = null;

        //�P�j�@@ID�`�F�b�N���s���B
        //���N�G�X�g�f�[�^.ID �� null�̏ꍇ�A�uID��null�v�̗�O���X���[����B
        if (l_request.id == null)
        {
            l_response =
                (WEB3EquityCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_00600;
            WEB3BusinessLayerException l_exp =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                    STR_METHOD_NAME);
            log.error("����ID�����w��ł��B", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�Q�j�@@����Ώے����P�ʂ��擾����
        //�@@�g�����������}�l�[�W��.getOrderUnits(���N�G�X�g�f�[�^.ID)
        //�Ŏ擾���������P�ʃI�u�W�F�N�g�̂����A
        //�@@�ŏ��̗v�f���擾����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        long l_lngOrderId = Long.parseLong(l_request.id);
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        if (l_orderUnits == null || l_orderUnits.length == 0)
        {
            l_response =
                (WEB3EquityCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
            WEB3BusinessLayerException l_exp =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    STR_METHOD_NAME);
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        OrderUnit l_orderUnit = l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //�R�j�@@�s��I�u�W�F�N�g���擾����B
        //�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket()���R�[������B
        //[.getMarket()�ɃZ�b�g�������]�s��ID:�P�j�Ŏ擾���������P��.�s��ID
        WEB3GentradeFinObjectManager l_objectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        WEB3GentradeMarket l_market = null;
        try
        {
            l_market = (WEB3GentradeMarket)l_objectManager.getMarket(l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_ex)
        {
            l_response =
                (WEB3EquityCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�S�j�@@�s��.isPTS�s��i�j���APTS�s�ꂩ���肵�A�e�������Ăѕ�����B
        //�S�|�P�j�@@PTS�s��łȂ��ꍇ�i�s��.isPTS�s��()==false�j
        //������������T�[�r�X�I�u�W�F�N�g���擾���Aexecute�i�j���R�[������B
        boolean l_blnIsPTSMarket = false;
        try
        {
            l_blnIsPTSMarket = l_market.isPTSMarket();
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3EquityCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80003;
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        if (!l_blnIsPTSMarket)
        {
            WEB3EquityCancelOrderService l_service = null;
            try
            {
                l_service =
                    (WEB3EquityCancelOrderService) Services.getService(
                        WEB3EquityCancelOrderService.class);
            }
            catch (Exception e)
            {
                l_response =
                    (WEB3EquityCancelCompleteResponse) l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error(
                    l_request, "������������T�[�r�X�̎擾�Ɏ��s���܂����B", l_response.errorInfo, e);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            
            
            try
            {
                l_response =
                    (WEB3EquityCancelCompleteResponse) l_service.execute(l_request);
            }
            catch (WEB3BaseException e)
            {
                l_response =
                    (WEB3EquityCancelCompleteResponse) l_request.createResponse();
                l_response.errorInfo = e.getErrorInfo();
                log.error(l_request, "����������������Ɏ��s���܂����B", e);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            catch (WEB3BaseRuntimeException l_bre)
            {
                l_response =
                    (WEB3EquityCancelCompleteResponse) l_request.createResponse();
                l_response.errorInfo = l_bre.getErrorInfo();
                log.error(l_request, "����������������Ɏ��s���܂����B", l_bre);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
        }
        //�S�|�Q�j�@@PTS�s��̏ꍇ�i�s��.isPTS�s��()==true�j
        //(PTS)������������T�[�r�X�I�u�W�F�N�g���擾���Aexecute�i�j���R�[������B
        else
        {
            WEB3EquityPTSCancelOrderService l_service = null;
            try
            {
                l_service =
                    (WEB3EquityPTSCancelOrderService)Services.getService(
                        WEB3EquityPTSCancelOrderService.class);
            }
            // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            catch (Exception l_ex)
            {
                l_response =
                    (WEB3EquityCancelCompleteResponse)l_request.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error(
                    l_request,
                    "(PTS)������������T�[�r�X�̎擾�Ɏ��s���܂����B",
                    l_response.errorInfo,
                    l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }

            try
            {
                l_response =
                    (WEB3EquityCancelCompleteResponse)l_service.execute(l_request);
            }
            catch (WEB3BaseException l_ex)
            {
                l_response =
                    (WEB3EquityCancelCompleteResponse)l_request.createResponse();
                l_response.errorInfo = l_ex.getErrorInfo();
                log.error(l_request, "(PTS)����������������Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            catch (WEB3BaseRuntimeException l_ex)
            {
                l_response =
                    (WEB3EquityCancelCompleteResponse)l_request.createResponse();
                l_response.errorInfo = l_ex.getErrorInfo();
                log.error(l_request, "(PTS)����������������Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

}
@
