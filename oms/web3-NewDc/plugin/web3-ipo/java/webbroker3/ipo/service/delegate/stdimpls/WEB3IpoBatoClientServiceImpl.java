head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBatoClientServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�d�q���ڑ��T�[�r�XImpl(WEB3IpoBatoClientServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/26 ���g(���u) �V�K�쐬
*/
package webbroker3.ipo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.message.WEB3GentradeBatoDisplayCommonResponse;
import webbroker3.gentrade.message.WEB3GentradeProspectusDisplayRequest;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.ipo.WEB3IpoClientRequestService;
import webbroker3.ipo.WEB3IpoOrderManagerImpl;
import webbroker3.ipo.WEB3IpoOrderValidator;
import webbroker3.ipo.service.delegate.WEB3IpoBatoClientService;
import webbroker3.ipo.message.WEB3IPOBatoUrlRequest;
import webbroker3.ipo.message.WEB3IPOBatoUrlResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (IPO�d�q���ڑ��T�[�r�XImpl)<BR>
 * IPO�d�q���ڑ��T�[�r�X�����N���X
 * 
 * @@author ���g
 * @@version 1.0
 */
public class WEB3IpoBatoClientServiceImpl extends WEB3IpoClientRequestService 
    implements WEB3IpoBatoClientService 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IpoBatoClientServiceImpl.class);    
    
    /**
     * @@roseuid 43D8344F0213
     */
    public WEB3IpoBatoClientServiceImpl() 
    {
     
    }
    
    /**
     * IPO�ɂ�����d�q���ڑ����������{����B<BR>
     * <BR>
     * this.get�d�q��URL()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 43D080E301D7
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3IPOBatoUrlRequest)
        {
            l_response = this.getBatoUrl((WEB3IPOBatoUrlRequest)l_request);
        }
        else
        {
            log.error("�p�����[�^.���N�G�X�g�f�[�^�̌^���s���ł��B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);             
        return l_response;
    }
    
    /**
     * (get�d�q��URL)<BR>
     * IPO�ɂ�����d�q��URL�擾�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�d�q���ڑ��jget�d�q��URL�v�Q�ƁB
     * @@param l_request - IPO�d�q��URL�擾���N�G�X�g
     * @@return webbroker3.ipo.message.WEB3IPOBatoUrlResponse
     * @@throws WEB3BaseException
     * @@roseuid 43D0816A00AE
     */
    protected WEB3IPOBatoUrlResponse getBatoUrl(WEB3IPOBatoUrlRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " getBatoUrl(WEB3IPOBatoUrlRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.error("�p�����[�^.���N�G�X�g�f�[�^��null�ł��B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.1 validate������t�\()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.2 get�⏕����()
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();

        //1.3 getOrderValidator()
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        WEB3IpoOrderManagerImpl l_orderManagerImpl =
            (WEB3IpoOrderManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();

        WEB3IpoOrderValidator l_ipoOrderValidator =  (WEB3IpoOrderValidator)l_orderManagerImpl.getOrderValidator();

        //1.4 validate���Z��(�⏕���� : �⏕����)
        l_ipoOrderValidator.validateResident(l_subAccount);

        //1.5 execute(���N�G�X�g�f�[�^ : WEB3GenRequest)
        WEB3GentradeBatoClientService l_batoClientService =
            (WEB3GentradeBatoClientService) Services.getService(WEB3GentradeBatoClientService.class);
            
        WEB3GentradeProspectusDisplayRequest l_prospectusDisplayRequest = new WEB3GentradeProspectusDisplayRequest();
        
        WEB3GentradeBatoDisplayCommonResponse l_batoDisplayCommonResponse =
            (WEB3GentradeBatoDisplayCommonResponse) l_batoClientService.execute(l_prospectusDisplayRequest);

        //1.6 createResponse()
        WEB3IPOBatoUrlResponse l_response = (WEB3IPOBatoUrlResponse) l_request.createResponse();

        //1.7  �v���p�e�B�Z�b�g
        l_response.isWorking = l_batoDisplayCommonResponse.isWorking;
        l_response.url = l_batoDisplayCommonResponse.url;
        l_response.hashValue = l_batoDisplayCommonResponse.hashValue;
       
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
}
@
