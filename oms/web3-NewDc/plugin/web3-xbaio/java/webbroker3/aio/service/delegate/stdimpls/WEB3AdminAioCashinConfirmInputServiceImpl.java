head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.29.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinConfirmInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A���m�F���̓T�[�r�X�����N���X(WEB3AdminAioCashinConfirmInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 ��O�� (���u) �V�K�쐬   
                   2004/10/25 ���z (���u) ���r���[                 
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.message.WEB3AdminAioCashinConfirmInputResponse;
import webbroker3.aio.message.WEB3AioFinancialInstitutionUnit;
import webbroker3.aio.service.delegate.WEB3AdminAioCashinConfirmInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����A���m�F���̓T�[�r�XImpl)<BR>
 * �����A���m�F���̓T�[�r�X�����N���X<BR>
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AdminAioCashinConfirmInputServiceImpl extends WEB3ClientRequestService implements WEB3AdminAioCashinConfirmInputService 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashinConfirmInputServiceImpl.class);
        
    /**
     * �����A���m�F���̓T�[�r�X�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����A���m�F���́j���͉�ʕ\���f�[�^�擾�v �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4107499D0136
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3AdminAioCashinConfirmInputResponse l_adminAioCashinConfirmInputResponse = null;        

        //1.1 �O���c�Ɠ����擾����B 
        //�m�����n 
        //����F �V�X�e���^�C���X�^���v 
        //���Z�^���Z�����F -1        
        
        Date l_datBizDate = new WEB3GentradeBizDate(
            GtlUtils.getTradingSystem().getSystemTimestamp()).roll(-1);
            
        log.debug("�O���c�Ɠ� = " + l_datBizDate);
        
        //1.2 �����̎�t���؎��Ԃ��擾����B 

        //�m�����n 
        //�s��R�[�h�F 0�iDEFAULT) 
        //���i�R�[�h�F 0�iDEFAULT) 
        String l_strTradeCloseTime =
            WEB3GentradeTradingTimeManagement.getTradeCloseTime(
                WEB3MarketCodeDef.DEFAULT, WEB3ProductCodeDef.DEFAULT);
        log.debug("�����̎�t���؎��� = " + l_strTradeCloseTime);
        
        //1.3 �Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
               
        //1.4 �،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_web3Administrator.getInstitutionCode();
        log.debug("�،���ЃR�[�h = " + l_strInstitutionCode);     
        
        //1.5 ���X�R�[�h���擾����B
        String l_strBranchCode = l_web3Administrator.getBranchCode();
        log.debug("���X�R�[�h = " + l_strBranchCode);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_web3AioOrderMgr =
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
                   
        //1.6 �Y���̏،���Ђŗ��p�\�ȐU������Z�@@�֖��׃��X�g���擾����B 
        //�m�����n 
        //�،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l 
        //���X�R�[�h�F null
        
        WEB3AioFinancialInstitutionUnit[] l_web3AioFinancialInstitutionUnits = 
            l_web3AioOrderMgr.createFinancialInstitutionDetails(
                l_strInstitutionCode, null);
        
        //1.7 ���X�|���X�f�[�^�𐶐�����B
        l_adminAioCashinConfirmInputResponse = (WEB3AdminAioCashinConfirmInputResponse) 
            l_request.createResponse();
        
        //1.8  �v���p�e�B�Z�b�g
        //(*) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        
        //���X�|���X.���X�R�[�h = �Ǘ���.get���X�R�[�h()�̖߂�l
        l_adminAioCashinConfirmInputResponse.branchCode = l_strBranchCode;
        log.debug("���X�|���X.���X�R�[�h = " + 
                l_adminAioCashinConfirmInputResponse.branchCode);
        
        //���X�|���X.�A�������i���j = �i�ȉ��̂Ƃ���j
        //    �c�Ɠ��v�Z.calc�c�Ɠ�()�Ŏ擾�����O���c�Ɠ��̓��t��
        //    ������ԊǗ�.get�s��ǎ���()�Ŏ擾����������t���؎�����ҏW��������
        
        //=======remain zhou-yong NO.1 2004/12/14 begin ========
        
        l_adminAioCashinConfirmInputResponse.minNoticeDate = 
            WEB3DateUtility.getDate(WEB3DateUtility.formatDate(
                l_datBizDate, "yyyyMMdd") + l_strTradeCloseTime, 
                    "yyyyMMddHHmmss");

        log.debug("���X�|���X.�A�������i���j = " + 
                l_adminAioCashinConfirmInputResponse.minNoticeDate);
        
        //���X�|���X.�A�������i���j = �i�ȉ��̂Ƃ���j
        //    �V�X�e���^�C���X�^���v����擾���������c�Ɠ��̓��t��
        //    ������ԊǗ�.get�s��ǎ���()�Ŏ擾����������t���؎�����ҏW��������
        
        l_adminAioCashinConfirmInputResponse.maxNoticeDate =
            WEB3DateUtility.getDate(WEB3DateUtility.formatDate(
                GtlUtils.getTradingSystem().getSystemTimestamp(), "yyyyMMdd") + 
                    l_strTradeCloseTime, "yyyyMMddHHmmss");
        
        //=======remain zhou-yong NO.1 2004/12/14 end ========
        
        log.debug("���X�|���X.�A�������i���j = " + 
                l_adminAioCashinConfirmInputResponse.maxNoticeDate);
        
        //���X�|���X.�U������Z�@@�ֈꗗ = ���o�������}�l�[�W��.create�U������Z�@@�֖���()�̖߂�l
        l_adminAioCashinConfirmInputResponse.financialInstitutionUnits = 
            l_web3AioFinancialInstitutionUnits;
        
        log.exiting(STR_METHOD_NAME);
        return l_adminAioCashinConfirmInputResponse;
    }
}
@
