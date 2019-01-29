head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.27.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSLRepayApplyInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���ԍϐ\�����̓T�[�r�XImpl(WEB3AioSLRepayApplyInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 �đo�g (���u) �V�K�쐬 �d�l�ύX���f��757,���f��773
Revesion History : 2007/11/08  �|�� (SCS) �o�O�C��
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.message.WEB3SLRepayApplyInputResponse;
import webbroker3.aio.service.delegate.WEB3AioSLRepayApplyInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�،��S�ۃ��[���ԍϐ\�����̓T�[�r�XImpl)<BR>
 * �،��S�ۃ��[���ԍϐ\�����̓T�[�r�X�����N���X<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3AioSLRepayApplyInputServiceImpl extends WEB3ClientRequestService
    implements WEB3AioSLRepayApplyInputService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3AioSLRepayApplyInputServiceImpl.class);

    /**
     * @@roseuid 46E890830358
     */
    public WEB3AioSLRepayApplyInputServiceImpl()
    {

    }

    /**
     * �،��S�ۃ��[���ԍϐ\�����̓T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�،��S�ۃ��[���ԍϐ\�����́j���͉�ʕ\���f�[�^�擾�v �Q��<BR>
     * <BR>
     * ========================================================<BR>
     * �V�[�P���X�}:�i�،��S�ۃ��[���ԍϐ\�����́j���͉�ʕ\���f�[�^�擾<BR>
     * ��̈ʒu�Fis�،��S�ۃ��[�������J��( )<BR>
     * �@@�߂�l == false �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02914<BR>
     * ========================================================<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
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

        //get�⏕����(�⏕�����^�C�v : SubAccountTypeEnum)
        //[����]
        //�⏕�����^�C�v�F 1�i�a��������j
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //validate����(SubAccount)
        //[����]
        //�⏕�����F get�⏕����()�̖߂�l
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        l_aioOrderManager.validateOrder(l_subAccount);

        //getMainAccount( )
        WEB3GentradeMainAccount l_gentradeMainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //is�،��S�ۃ��[�������J��( )
        boolean l_blnIsSecuredLoanAccountOpen = l_gentradeMainAccount.isSecuredLoanAccountOpen();
        if (!l_blnIsSecuredLoanAccountOpen)
        {
            //�߂�l == false �̏ꍇ�A��O���X���[����B
            log.debug("�،��S�ۃ��[�����������J�݂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02914,
                this.getClass().getName() + STR_METHOD_NAME,
                "�،��S�ۃ��[�����������J�݂ł��B");
        }

        //get������( )
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //ArrayList( )
        List l_lisEstimatedTransferDates = new ArrayList();

        //get���ߐU����(SubAccount, Date)
        //[����]
        //�⏕�����F get�⏕����()�̖߂�l
        //�������F get������()�̖߂�l
        Date l_datClosestTransferDate =
            l_aioOrderManager.getClosestTransferDate(l_subAccount, l_datOrderBizDate);

        //add(arg0 : Object)
        //[����]  
        //arg0�F get���ߐU����()�̖߂�l
        l_lisEstimatedTransferDates.add(l_datClosestTransferDate);

        //getInstitution( )
        Institution l_insititution = l_subAccount.getInstitution();

        //calc�c�Ɠ�(��� : Timestamp, ���Z�^���Z���� : int)
        //[����]
        //����F ���ݓ��t
        //���Z�^���Z�����F 4
        Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
        WEB3GentradeBizDate l_gentradeCurrentTime =
            new WEB3GentradeBizDate(l_tsSystemTime);
        Timestamp l_tsFourDayAfter = l_gentradeCurrentTime.roll(4);

        //�،����.getDateSourceObject().�o���\����{ > 0 �̏ꍇ
        //���̒l�̉񐔂�Loop�������s���B
        InstitutionRow l_institutionRow =
            (InstitutionRow)l_insititution.getDataSourceObject();
        int l_intCnt = 0;
        if (l_institutionRow.getPaymentReserve() != null)
        {
            l_intCnt = Integer.parseInt(l_institutionRow.getPaymentReserve());
        }
        if (l_intCnt > 0)
        {
            for (int i = 1; i <= l_intCnt; i++)
            {
                //calc�c�Ɠ�(��� : Timestamp, ���Z�^���Z���� : int)
                //[����]
                //����F get���ߐU����() �̖߂�l
                //���Z�^���Z�����FLoop��
                WEB3GentradeBizDate l_gentradeTempDate =
                    new WEB3GentradeBizDate(new Timestamp(l_datClosestTransferDate.getTime()));
                Timestamp l_tsTempTime = l_gentradeTempDate.roll(i);
                if (WEB3DateUtility.compareToDay(l_tsTempTime, l_tsFourDayAfter) <= 0)
                {
                    //�Z�o�����ԍϗ\����̓��t > ���ݓ��t��4����̓��t �̏ꍇ�A
                    //���X�g�ɒǉ����Ȃ��B
                    l_lisEstimatedTransferDates.add(l_tsTempTime);
                }
            }
        }

        //toArray( )
        Date[] l_datRepayScheduledDates = new Date[l_lisEstimatedTransferDates.size()];
        l_lisEstimatedTransferDates.toArray(l_datRepayScheduledDates);
 
        //ArrayList( )
        List l_lisRepayableAmts = new ArrayList();
        for (int i = 0; i < l_datRepayScheduledDates.length; i++)
        {
            //get�S�ۃ��[���U�։\�z(�⏕���� : �⏕����, ��n�� : Date)
            //[����]
            //�⏕�����F get�⏕����()�̖߂�l
            //��n���F �ԍϗ\������X�g�̗v�f
            WEB3TPTradingPowerService l_tradingPowerService =
                (WEB3TPTradingPowerService)Services.getService(
                    WEB3TPTradingPowerService.class);
            double l_dblSLChangePossAmt =
                l_tradingPowerService.getSLChangePossAmt(
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_datRepayScheduledDates[i]);

            //add(arg0 : Object)
            //[����]
            //arg0�F get�S�ۃ��[���U�։\�z()�̖߂�l
            l_lisRepayableAmts.add(WEB3StringTypeUtility.formatNumber(l_dblSLChangePossAmt));
        }

        //toArray( )
        String[] l_strRepayableAmts = new String[l_lisRepayableAmts.size()];
        l_lisRepayableAmts.toArray(l_strRepayableAmts);

        //createResponse( )
        WEB3SLRepayApplyInputResponse l_response =
            (WEB3SLRepayApplyInputResponse)l_request.createResponse();

        //�v���p�e�B�Z�b�g
        //���X�|���X.�ԍω\�z = �ԍω\�z���X�g
        l_response.repayableAmtList = l_strRepayableAmts;

        //���X�|���X.�ԍϗ\��� = �U���\������X�g
        l_response.repayScheduledDateList = l_datRepayScheduledDates;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
