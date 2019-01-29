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
filename	WEB3ExpirationDateListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����L�������擾�T�[�r�XImpl(WEB3ExpirationDateListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/14 ���n(���u) �V�K�쐬���f��319
Revision History : 2008/02/18 ���n(���u) �d�l�ύX���f��322
Revision History : 2008/02/19 ���n(���u) �d�l�ύX���f��323
*/

package webbroker3.gentrade.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.message.WEB3ExpirationDateListRequest;
import webbroker3.gentrade.message.WEB3ExpirationDateListResponse;
import webbroker3.gentrade.service.delegate.WEB3ExpirationDateListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����L�������擾�T�[�r�XImpl)<BR>
 * �����L�������擾�T�[�r�X�����N���X<BR>
 *
 * @@author ���n
 * @@version 1.0
 */
public class WEB3ExpirationDateListServiceImpl implements WEB3ExpirationDateListService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ExpirationDateListServiceImpl.class);

    /**
     * @@roseuid 47B3E6480109
     */
    public WEB3ExpirationDateListServiceImpl()
    {

    }

    /**
     * �����L�������擾�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}�u�����L�������擾�v�Q��<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A9761B02C6
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        WEB3ExpirationDateListRequest l_expirationDateListRequest = null;
        if (l_request instanceof WEB3ExpirationDateListRequest)
        {
            l_expirationDateListRequest =
                ((WEB3ExpirationDateListRequest)l_request);
            l_expirationDateListRequest.validate();
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        WEB3GentradeHandlingOrderCond l_gentradeHandingOrderCond = null;

        //�،���ЃR�[�h = OpLoginSecurityService���ҏW���ݒ�
        //�����R�[�h
        long l_lngAccountId = 0L;
        //�،���ЃR�[�h
        String l_strInstitutionCode = null;
        //�Z�L�����e�B�T�[�r�X���擾
        try
        {
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(
                    OpLoginSecurityService.class);
            //AccountId���擾
            l_lngAccountId = l_opLoginSec.getAccountId();
            //FinApp�T�[�r�X���擾
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accMgr = l_finApp.getAccountManager();
            MainAccount l_acc;
            l_acc = l_accMgr.getMainAccount(l_lngAccountId);
            l_strInstitutionCode = l_acc.getInstitution().getInstitutionCode();
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�����^�C�v =
        //�@@�E���N�G�X�g.���i�敪 = �h���������h�܂��́A�h�M�p����h�̏ꍇ�A�h�����h��ݒ�
        //�@@�E���N�G�X�g.���i�敪 = �h�敨�h�܂��́A�h�I�v�V�����h�̏ꍇ�A�h�敨�I�v�V�����h��ݒ�
        ProductTypeEnum l_productTypeEnum = null;
        if (WEB3CommodityDivDef.EQUITY.equals(l_expirationDateListRequest.commodityType)
            || WEB3CommodityDivDef.MARGIN.equals(l_expirationDateListRequest.commodityType))
        {
            l_productTypeEnum = ProductTypeEnum.EQUITY;
        }
        else if (WEB3CommodityDivDef.FUTURE.equals(l_expirationDateListRequest.commodityType)
            || WEB3CommodityDivDef.OPTION.equals(l_expirationDateListRequest.commodityType))
        {
            l_productTypeEnum = ProductTypeEnum.IFO;
        }

        //�敨�^�I�v�V�����敪 =
        //�@@�E���N�G�X�g.���i�敪 = �h�敨�h�̏ꍇ�A�h�敨�h��ݒ�
        //�@@�E���N�G�X�g.���i�敪 = �h�I�v�V�����h�̏ꍇ�A�h�I�v�V�����h��ݒ�
        //�@@�E�ȊO�A�hDEFAULT�h��ݒ�
        String l_strFuturesOptionDivDef = null;
        if (WEB3CommodityDivDef.FUTURE.equals(l_expirationDateListRequest.commodityType))
        {
            l_strFuturesOptionDivDef = WEB3FuturesOptionDivDef.FUTURES;
        }
        else if (WEB3CommodityDivDef.OPTION.equals(l_expirationDateListRequest.commodityType))
        {
            l_strFuturesOptionDivDef = WEB3FuturesOptionDivDef.OPTION;
        }
        else
        {
            l_strFuturesOptionDivDef = WEB3FuturesOptionDivDef.DEFAULT;
        }

        //�M�p����敪 = �hDEFAULT�h(�Œ�) ��ݒ�
        //�s��R�[�h =
        //�@@�E���N�G�X�g.���i�敪 = �h���������h�܂��́A�h�M�p����h�̏ꍇ�A���N�G�X�g.�s��R�[�h��ݒ�
        //�@@�E���N�G�X�g.���i�敪 = �h�敨�h�܂��́A�h�I�v�V�����h�̏ꍇ�A�hDEFAULT�h��ݒ�
        String l_strMarketCode = null;
        if (WEB3CommodityDivDef.EQUITY.equals(l_expirationDateListRequest.commodityType)
            || WEB3CommodityDivDef.MARGIN.equals(l_expirationDateListRequest.commodityType))
        {
            l_strMarketCode = l_expirationDateListRequest.marketCode;
        }
        else if (WEB3CommodityDivDef.FUTURE.equals(l_expirationDateListRequest.commodityType)
            || WEB3CommodityDivDef.OPTION.equals(l_expirationDateListRequest.commodityType))
        {
            l_strMarketCode = WEB3MarketCodeDef.DEFAULT;
        }

        l_gentradeHandingOrderCond = new WEB3GentradeHandlingOrderCond(
            l_strInstitutionCode,
            l_productTypeEnum,
            l_strFuturesOptionDivDef,
            WEB3MarginTradingDivDef.DEFAULT,
            l_strMarketCode);

        //�戵�\���������敪���擾����
        String[] l_strExpirationDateTypes =
            l_gentradeHandingOrderCond.getHandlingPossibleExpirationDateType();

        //�o���܂Œ������戵�\�ł��邩�𔻒肷��
        boolean l_blnOrderUntilDeadLinePossibleHandling = false;
        boolean l_blnOrderUntilDeadLinePossibleNoHandlingTradingEndDateConsidering = false;
        if (WEB3CommodityDivDef.EQUITY.equals(l_expirationDateListRequest.commodityType)
            || WEB3CommodityDivDef.MARGIN.equals(l_expirationDateListRequest.commodityType))
        {
            l_blnOrderUntilDeadLinePossibleHandling =
                l_gentradeHandingOrderCond.isOrderUntilDeadLinePossibleHandling();
        }
        else if (WEB3CommodityDivDef.FUTURE.equals(l_expirationDateListRequest.commodityType)
            || WEB3CommodityDivDef.OPTION.equals(l_expirationDateListRequest.commodityType))
        {
            l_blnOrderUntilDeadLinePossibleNoHandlingTradingEndDateConsidering =
                l_gentradeHandingOrderCond.isOrderUntilDeadLinePossibleNoHandlingTradingEndDateConsidering();
        }

        //�����J�n��
        Date l_datStartDay = null;

        //�����ŏI��
        Date l_datEndDay = null;

        //�����������̏j���ꗗ
        Date[] l_datDateHolidays = null;

        if (l_blnOrderUntilDeadLinePossibleHandling
            || l_blnOrderUntilDeadLinePossibleNoHandlingTradingEndDateConsidering)
        {
            //�o����܂Œ����J�n�����擾����B
            l_datStartDay = l_gentradeHandingOrderCond.getOrderUntilDeadLineStartDay();
            //�o����܂Œ����ŏI�����擾����B
            l_datEndDay = l_gentradeHandingOrderCond.getOrderUntilDeadLineEndDay();
            //�o����܂Œ����������̏j���ꗗ���擾����B
            l_datDateHolidays = l_gentradeHandingOrderCond.getExpirationDateHoliday();
        }

        //����敪���擾����B
        String l_strSessionTypeTemp = WEB3GentradeTradingTimeManagement.getSessionType();

        //���X�|���X�f�[�^�𐶐�����
        //�y�����L�������擾���X�|���X�z
        //���������敪�ꗗ�F�戵�\��������.�戵�\���������敪�擾( )�̖߂�lList���Z�b�g
        //�L�������J�n���F�戵�\��������.get�o����܂Œ����J�n��( )�̖߂�l���Z�b�g(*1)
        //�L�������ŏI���F�戵�\��������.get�o����܂Œ����ŏI��( )�̖߂�l���Z�b�g(*1)
        //�L���������j���ꗗ�F�戵�\��������.get�����������j���ꗗ( )�̖߂�l���Z�b�g(*1)
        //����敪�F������ԊǗ�.get����敪( )�̖߂�l���Z�b�g
        //(*1)�@@�戵�\��������.is�o����܂Œ����戵�\( )==true�̏ꍇ�܂��́A
        //�@@ �戵�\��������.is�o����܂Œ����戵�\<����ŏI���l����>( )==true�̏ꍇ�̂݁A�Z�b�g����
        WEB3ExpirationDateListResponse l_response =
            (WEB3ExpirationDateListResponse)l_expirationDateListRequest.createResponse();
        l_response.expirationDateTypeList = l_strExpirationDateTypes;
        if (l_blnOrderUntilDeadLinePossibleHandling
            || l_blnOrderUntilDeadLinePossibleNoHandlingTradingEndDateConsidering)
        {
            l_response.expirationStartDate = l_datStartDay;
            l_response.expirationEndDate = l_datEndDay;
            l_response.holidayList = l_datDateHolidays;
        }
        l_response.sessionType = l_strSessionTypeTemp;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
