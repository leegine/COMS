head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradePasswordConvHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ïؔԍ��ϊ��n���h���N���X(WEB3GentradePasswordConvHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/15 ����(�r�q�`) �V�K�쐬
*/
package webbroker3.gentrade.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.message.WEB3GentradePasswordConvAccOpenRequest;
import webbroker3.gentrade.message.WEB3GentradePasswordConvAccOpenResponse;
import webbroker3.gentrade.message.WEB3GentradePasswordConvExpAccOpenRequest;
import webbroker3.gentrade.message.WEB3GentradePasswordConvExpAccOpenResponse;
import webbroker3.gentrade.message.WEB3GentradePasswordConvMainAccountRequest;
import webbroker3.gentrade.message.WEB3GentradePasswordConvMainAccountResponse;
import webbroker3.gentrade.message.WEB3GentradePasswordConvWeb2TransferRequest;
import webbroker3.gentrade.message.WEB3GentradePasswordConvWeb2TransferResponse;
import webbroker3.gentrade.message.WEB3GentradePasswordConvSonarTraderRequest;
import webbroker3.gentrade.message.WEB3GentradePasswordConvSonarTraderResponse;
import webbroker3.gentrade.service.delegate.WEB3GentradePasswordConvService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

/**
 * �Ïؔԍ��ϊ��n���h���N���X<br />
 * @@author �r�q�`����
 */
public class WEB3GentradePasswordConvHandler implements MessageHandler
{
	/**
	 * ���O�o�̓��[�e�B���e�B
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3GentradeBatoClientHandler.class);
    
	/**
	 * �f�t�H���g�R���X�g���N�^
	 */
	public WEB3GentradePasswordConvHandler() {} 

	/**
	 * �y�ڋq�}�X�^���ԃe�[�u���Ïؔԍ��X�V�z<br />
	 * SONAR�Í����`���̈Ïؔԍ����AxTrade-Hash�CWEB3�Í����`���ɕϊ���
	 * ���ԃe�[�u���ɍX�V����B<br />
	 * 
	 * @@param l_request ���N�G�X�g�f�[�^
	 * @@return WEB3GentradePasswordConvMainAccountResponse
	 */
	public WEB3GentradePasswordConvMainAccountResponse convMainAccountBuffer(
		WEB3GentradePasswordConvMainAccountRequest l_request
		)
	{
		final String STR_METHOD_NAME = 
			" convMainAccountBuffer(WEB3GentradePasswordConvMainAccountRequest)";    
		log.entering(STR_METHOD_NAME);

		WEB3GentradePasswordConvMainAccountResponse l_response = 
			(WEB3GentradePasswordConvMainAccountResponse)this.serviceCall(
				l_request
				);

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

    /**
     * �y�����J�݌����q�e�[�u���Ïؔԍ��X�V�z<br />
     * SONAR�Í����`���̈Ïؔԍ����AWEB3�Í����`���ɕϊ���
     * �����J�݌����q�e�[�u���ɍX�V����B<br />
     * 
     * @@param l_request ���N�G�X�g�f�[�^
     * @@return WEB3GentradePasswordConvMainAccountResponse
     */
    public WEB3GentradePasswordConvExpAccOpenResponse convExpAccOpenBuffer(
        WEB3GentradePasswordConvExpAccOpenRequest l_request
        )
    {
        final String STR_METHOD_NAME = 
            " convExpAccOpenBuffer(WEB3GentradePasswordConvExpAccOpenRequest)";    
        log.entering(STR_METHOD_NAME);

        WEB3GentradePasswordConvExpAccOpenResponse l_response = 
            (WEB3GentradePasswordConvExpAccOpenResponse)this.serviceCall(
                l_request
                );

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

	/**
	 * �y�����J�� �Ïؔԍ��`�[�iG5511�j�X�V�z<br />
	 * WEB3�Í����`���̈Ïؔԍ��𕜍������A���ԃe�[�u���ɍX�V����B<br />
	 * 
	 * @@param l_request ���N�G�X�g�f�[�^
	 * @@return WEB3GentradePasswordConvAccOpenResponse
	 */
	public WEB3GentradePasswordConvAccOpenResponse convAccOpenBuffer(
		WEB3GentradePasswordConvAccOpenRequest l_request
		) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = 
		" convAccOpenBuffer(WEB3GentradePasswordConvAccOpenRequest)";
		log.entering(STR_METHOD_NAME);
		
		WEB3GentradePasswordConvAccOpenResponse l_response = 
			(WEB3GentradePasswordConvAccOpenResponse)this.serviceCall(
				l_request
				);

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}
	
	/**
	 * �yWEB2�Ïؔԍ��f�[�^�ڊǁz<br />
	 * WEB2�Í����`���̈Ïؔԍ��𕜍������A���ԃe�[�u���ɍX�V����B<br />
	 * 
	 * @@param l_request ���N�G�X�g�f�[�^
	 * @@return WEB3GentradePasswordConvWeb2TransferResponse
	 */
	public WEB3GentradePasswordConvWeb2TransferResponse transfer(
		WEB3GentradePasswordConvWeb2TransferRequest l_request)
	{
		final String STR_METHOD_NAME = 
		" transfer(WEB3GentradePasswordConvWeb2TransferRequest)";
		log.entering(STR_METHOD_NAME);
		
		WEB3GentradePasswordConvWeb2TransferResponse l_response = 
			(WEB3GentradePasswordConvWeb2TransferResponse)this.serviceCall(
				l_request
				);

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * �֘A�T�[�r�X���R�[������
	 * 
	 * @@param l_request ���N�G�X�g�f�[�^
	 * @@return WEB3BackResponse
	 */
	private WEB3BackResponse serviceCall(WEB3BackRequest l_request)
	{
		WEB3GentradePasswordConvService l_service = null;
		WEB3BackResponse l_response = null;
        
		try
		{
			l_service = (WEB3GentradePasswordConvService)Services.getService(
					WEB3GentradePasswordConvService.class
					);
		} catch (Exception l_ex)
		{
			l_response = l_request.createResponse(); 
			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			log.error(
				l_request,
				"�Ïؔԍ��ϊ��T�[�r�X�̎擾�Ɏ��s���܂����B",
				l_response.errorInfo,
				l_ex);
			return l_response;   
		}
        
		try
		{
			l_response = (WEB3BackResponse)l_service.execute(l_request);
		} catch (WEB3BaseException l_ex)
		{
			l_response = (WEB3BackResponse)l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(l_request, 
				"�Ïؔԍ��ϊ��T�[�r�X�Ɏ��s���܂����B",
				l_ex);
			return l_response;
		} catch (Exception l_ex) 
		{
			l_response = (WEB3BackResponse)l_request.createResponse();
			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			log.error(
				l_request,
				"�Ïؔԍ��ϊ��T�[�r�X�Ɏ��s���܂����B",
				l_response.errorInfo,
				l_ex);
		}
		return l_response;
	}

	/**
     * �y���ԃe�[�u���i���ҏ��j�Ïؔԍ��ǉ��z
     * ���ԃe�[�u���i���ҏ��j����SONAR���҃R�[�h��xTrade�AWEB3�Í����`���ɕϊ�
     * ���A�Ή����ڂ��X�V�iDB-update�j����B
	 * 
	 * @@param l_request ���N�G�X�g�f�[�^
     * @@return WEB3GentradePasswordConvSonarTraderResponse
	 */
    public WEB3GentradePasswordConvSonarTraderResponse convSonarTrader(
		WEB3GentradePasswordConvSonarTraderRequest l_request)
	{
		final String STR_METHOD_NAME =
		" convSonarTrader(WEB3GentradePasswordConvSonarTraderRequest)";
		log.entering(STR_METHOD_NAME);

		WEB3GentradePasswordConvSonarTraderResponse l_response = 
			(WEB3GentradePasswordConvSonarTraderResponse)this.serviceCall(
				l_request
				);

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}
}
@
