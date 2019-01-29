head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.25.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioMultiBankSettleControlService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �}���`�o���N���ϐ���T�[�r�X(WEB3AioMultiBankSettleControlService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 ������ (���u) �V�K�쐬
                   2004/10/22 ��O�� (���u) ���r���[
                   2006/04/13 �юu�� (���u) �d�l�ύX�E���f��538
                   2006/04/13 �юu�� (���u) �d�l�ύX�E���f��540
*/
package webbroker3.aio;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import webbroker3.aio.message.WEB3AioPrSessionUnit;
import webbroker3.aio.message.WEB3AioSelectSettleInstitutionUnit;
import webbroker3.aio.message.WEB3AioSettleInstitutionUnit;
import webbroker3.common.WEB3BaseException;

/**
 * (�}���`�o���N���ϐ���T�[�r�X)<BR>
 * �}���`�o���N���ϐ���T�[�r�X�C���^�[�t�F�C�X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public interface WEB3AioMultiBankSettleControlService extends Service
{

    /**
     * (get�I�����ϋ@@�֖���)<BR>
     * �Y������،���ЁA���X�Ŏ戵���Ă��錈�ϋ@@�ւ̈ꗗ�Ǝ�t�󋵂��擾����B
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strBranchCode - (���X�R�[�h)
     * @@param l_strCareerDiv - (�L�����A�敪)
     * @@return WEB3AioSelectSettleInstitutionUnit
     * @@throws WEB3BaseException<BR>
     * @@roseuid 411721A6027A
     */
    public WEB3AioSelectSettleInstitutionUnit[] getSelectPaySchemeDetails(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strCareerDiv)
        throws WEB3BaseException;

    /**
     * (get��g���ϋ@@�֖���)<BR>
     * �Y������،���ЁA���X�Ŏ戵���Ă��錈�ϋ@@�ւ̈ꗗ���擾����B
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@return WEB3AioSettleInstitutionUnit
     * @@throws WEB3BaseException<BR>
     * @@roseuid 411721A60355
     */
    public WEB3AioSettleInstitutionUnit[] getAffiliatedPaySchemeDetails(
                String l_strInstitutionCode)
        throws WEB3BaseException;

    /**
     * (get���o����)<BR>
     * �I�����C�����o���̏󋵂��擾����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_strOrderRequestNumber - (���ʃR�[�h)<BR>
     * @@param l_OrderStatus - (�������)<BR>
     * @@param l_strOrderCancleStatus - (��������敪)<BR>
     * @@return String<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 411721A602C8
     */
    public String getCashTransSituation(
        SubAccount l_subAccount,
        String l_strOrderRequestNumber,
        OrderStatusEnum l_OrderStatus,
        String l_strOrderCancleStatus)
        throws WEB3BaseException;

    /**
     * (get���Z�@@�֖�)<BR>
     * �I�����C�������̌��ϋ@@�ւ̖��̂��擾����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_strOrderRequestNumber - (���ʃR�[�h)<BR>
     * @@return String
     * @@throws WEB3BaseException<BR>
     * @@roseuid 411721A60307
     */
    public String getFinInstitutionName(
        SubAccount l_subAccount,
        String l_strOrderRequestNumber)
        throws WEB3BaseException;

    // =========remian zhou-yong NO.1 begin ========
    
    /**
     * (create���ψ˗�URL)<BR>
     * ���ψ˗��̍ۂɕԋp���郊�_�C���N�gURL�̕�����𐶐�����B
     * @@param l_prSessionUnit - (PR�w�ێ����)
     * @@param l_subAccount - (�⏕����)<BR><BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_strPaySchemeId - (���ϋ@@��ID)<BR>
     * @@param l_strOrderRequestNumber - (���ʃR�[�h)<BR>
     * @@return String
     * @@throws WEB3BaseException<BR>
     * @@roseuid 411721A603A3
     */
    public String createSettlementRequestURL(
        WEB3AioPrSessionUnit l_prSessionUnit,
        SubAccount l_subAccount,
        String l_strPaySchemeId,
        String l_strOrderRequestNumber)
        throws WEB3BaseException;

    // =========remian zhou-yong NO.1 end ========
    
    /**
     * (insert���o����)<BR>
     * ���ψ˗��̃X�e�[�^�X�ł̋��Z�@@�֘A�g���o���󋵃e�[�u���̃��R�[�h�𐶐�����B
     * @@param l_trader - (�㗝���͎�)<BR>
     * �㗝���͎҃I�u�W�F�N�g<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_strPaySchemeId - (���ϋ@@��ID)<BR>
     * @@param l_strNetAmount - (���z)<BR>
     * �������z<BR>
     * @@param l_datBizDate - (������)
     * @@param l_strOrderRequestNumber - (���ʃR�[�h)<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 411721A6023B
     */
    public void insertCashTransSituation(
        Trader l_trader,
        SubAccount l_subAccount,
        String l_strPaySchemeId,
        String l_strNetAmount,
        Date l_datBizDate,
        String l_strOrderRequestNumber)
        throws WEB3BaseException;

    /**
     * (validate��M�d��)<BR>
     * ��M�d���̃`�F�b�N���s���B<BR>
     * @@param l_receiptTelegramData - (��M�d���f�[�^)<BR>
     * @@param l_cashTransStatus - (���o����)<BR>
     * ���Z�@@�֘A�g���o����Params�I�u�W�F�N�g<BR>
     * @@return String<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 4119AD810244
     */
    public String validateReceiptTelegram(
        HashMap l_receiptTelegramData,
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus);

    /**
     * (create���M�d��)<BR>
     * ���M�d���𐶐�����B
     * @@param l_response - (���X�|���X�f�[�^)
     * @@param l_strSendTelegramDatas - (���M�d���f�[�^)
     * @@throws WEB3BaseException<BR>
     * @@roseuid 4119AD8103AB
     */
    public void createSendTelegram(
        HttpServletResponse l_response,
        String[] l_strSendTelegramDatas)
        throws IOException;

    /**
     * (insert�������v��)<BR>
     * �������v���e�[�u���Ƀ��R�[�h��ǉ�����B<BR>
     * @@param l_receiptTelegramData - (��M�d���f�[�^)<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 4119AD82010B
     */
    public void insertOrderInfoRequire(HashMap l_receiptTelegramData)
        throws WEB3BaseException;

    /**
     * (insert���ϊJ�n�v��)<BR>
     * ���ϊJ�n�v���e�[�u���Ƀ��R�[�h��ǉ�����B
     * @@param l_receiptTelegramData - (��M�d���f�[�^)
     * @@throws WEB3BaseException<BR>
     * @@roseuid 4119CF14038C
     */
    public void insertSettleStartRequire(HashMap l_receiptTelegramData)
        throws WEB3BaseException;

    /**
     * (insert���ό��ʒʒm)<BR>
     * ���ό��ʒʒm�e�[�u���Ƀ��R�[�h��ǉ�����B
     * @@param l_ReceiptTelegramData - (��M�d���f�[�^)
     * @@throws WEB3BaseException<BR>
     * @@roseuid 4119F79E033E
     */
    public void insertSettleResultNotify(HashMap l_ReceiptTelegramData)
        throws WEB3BaseException;

    /**
     * (update�����v����t)<BR>
     * ���Z�@@�֘A�g���o���󋵃e�[�u���𒍕���t���̏�ԂɍX�V����B<BR>
     * @@param l_strReturnCode - (��M�d���̃`�F�b�N����)<BR>
     * @@param l_strComDebitNumber - (.com�f�r�b�g���ώ���ԍ�)<BR>
     * @@param l_cashTransStatus - (���o����)<BR>
     * @@throws WEB3BaseException<BR>
     * ���Z�@@�֘A�g���o����Params�I�u�W�F�N�g
     * @@roseuid 4119AD8201F5
     */
    public void updateOrderRequireAccept(
        String l_strReturnCode,
        String l_strComDebitNumber,
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)
        throws WEB3BaseException;

    /**
     * (update�����v������)<BR>
     * ���Z�@@�֘A�g���o���󋵃e�[�u���𒍕���t�������̏�ԂɍX�V����B
     * @@param l_cashTransStatus - (���o����)<BR>
     * @@throws WEB3BaseException<BR>
     * ���Z�@@�֘A�g���o����Params�I�u�W�F�N�g
     * @@roseuid 4119AD8203CA
     */
    public void updateOrderRequireResponse(
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)
            throws WEB3BaseException;

    /**
     * (update�����v�����~)<BR>
     * ���Z�@@�֘A�g���o���󋵃e�[�u���𒍕���t���~���̏�ԂɍX�V����B
     * @@param l_cashTransStatus - (���o����)<BR>
     * @@throws WEB3BaseException<BR>
     * ���Z�@@�֘A�g���o����Params�I�u�W�F�N�g
     * @@roseuid 4119ADF901E6
     */
    public void updateOrderRequireDiscontinuation(
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)
            throws WEB3BaseException;

    /**
     * (update���ϊJ�n��t)<BR>
     * ���Z�@@�֘A�g���o���󋵃e�[�u�������ϊJ�n��t���̏�ԂɍX�V����B
     * @@param l_strReturnCode - (��M�d���̃`�F�b�N����)
     * @@param l_cashTransSituation - (���o����)<BR>
     * @@throws WEB3BaseException<BR>
     * ���Z�@@�֘A�g���o���󋵃I�u�W�F�N�g
     * @@roseuid 4119CDB700CD
     */
    public void updateSettleStartAccept(
        String l_strReturnCode,
        WEB3AioFinInstitutionCashTransStatus l_cashTransSituation)
        throws WEB3BaseException;

    /**
     * (update���ϊJ�n����)<BR>
     * ���Z�@@�֘A�g���o���󋵃e�[�u�������ϊJ�n�������̏�ԂɍX�V����B
     * @@param l_cashTransStatus - (���o����)<BR>
     * ���Z�@@�֘A�g���o����Params�I�u�W�F�N�g
     * @@roseuid 4119D031030F
     */
    public void updateSettleStartResponse(
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)
            throws WEB3BaseException;

    /**
     * (update���ό��ʒʒm)<BR>
     * ���Z�@@�֘A�g���o���󋵃e�[�u�������ό��ʒʒm���̏�ԂɍX�V����B
     * @@param l_strReturnCode - (��M�d���̃`�F�b�N����)
     * @@param l_datDeliveryScheduledDate - (��n�\���)
     * @@param l_datComondebiCaptureDate - (�U�������\���)
     * @@param l_cashTransSituation - (���o����)<BR>
     * @@throws WEB3BaseException<BR>
     * ���Z�@@�֘A�g���o���󋵃I�u�W�F�N�g
     * @@roseuid 4119F7460263
     */
    public void updateSettleResultNotify(
        String l_strReturnCode,
        Date l_datDeliveryScheduledDate,
        Date l_datComondebiCaptureDate,
        WEB3AioFinInstitutionCashTransStatus l_cashTransSituation)
        throws WEB3BaseException;

    /**
     * (update���ό��ʉ���)<BR>
     * ���Z�@@�֘A�g���o���󋵃e�[�u�������ό��ʉ������̏�ԂɍX�V����B
     * @@param l_cashTransStatus - (���o����)<BR>
     * @@throws WEB3BaseException<BR>
     * ���Z�@@�֘A�g���o����Params�I�u�W�F�N�g
     * @@roseuid 4119F746032E
     */
    public void updateSettleResultResponse(
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)
            throws WEB3BaseException;

    /**
     * (update���ϊ���)<BR>
     * ���Z�@@�֘A�g���o���󋵃e�[�u�������ϊ������i�G���[�j�̏�ԂɍX�V����B
     * @@param l_cashTransStatus - (���o����)<BR>
     * @@throws WEB3BaseException<BR>
     * ���Z�@@�֘A�g���o����Params�I�u�W�F�N�g
     * @@roseuid 411B189B033E
     */
    public void updateSettleComplete(
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)
            throws WEB3BaseException;

    /**
     * (update���σG���[)<BR>
     * ���Z�@@�֘A�g���o���󋵃e�[�u�������σG���[���̏�ԂɍX�V����B
     * @@param l_cashTransStatus - (���o����)<BR>
     * @@throws WEB3BaseException<BR>
     * ���Z�@@�֘A�g���o����Params�I�u�W�F�N�g
     * @@roseuid 411B32420235
     */
    public void updateSettleError(
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)
            throws WEB3BaseException;

    /**
     * (get���b�Z�[�W�R�[�h)<BR>
     * ���b�Z�[�W�R�[�h���擾����B
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strBranchCode - (���X�R�[�h)
     * @@param l_strAccountCode - (�ڋq�R�[�h)
     * @@param l_strOrderRequestNumber - (���ʃR�[�h)
     * @@param l_strComDebitNumber - (.com�f�r�b�g���ώ���ԍ�)
     * @@param l_strStatus - (�����敪)
     * @@param l_strOrderStatusFlag - (����FLAG�i�����j)
     * @@param l_strStartStatusFlag - (����FLAG�i���ϊJ�n�j)
     * @@param l_strResultStatusFlag - (����FLAG�i���ό��ʁj)
     * @@return String
     * @@throws WEB3BaseException<BR>
     * @@roseuid 4119AD83011B
     */
    public String getMessageCode(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strOrderRequestNumber,
        String l_strComDebitNumber,
        String l_strStatus,
        String l_strOrderStatusFlag,
        String l_strStartStatusFlag,
        String l_strResultStatusFlag)
        throws WEB3BaseException;

    /**
     * (createHashMapFrom��M�d��)<BR>
     * ��M�d������AHashMap�𐶐�����B
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return HashMap
     * @@throws WEB3BaseException<BR>
     * @@roseuid 4119AD8302FF
     */
    public HashMap createHashMapFromReceiptTelegram(
                HttpServletRequest l_request)
        throws IOException, WEB3BaseException;
    
    /**
     * (get�v���t�@@�����X)<BR>
     * �p�����[�^�Ɏw�肳�ꂽ�ݒ薼�̂̐ݒ�l���V�X�e���v���t�@@�����X�e�[�u������擾����B<BR> 
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strName - (�ݒ薼��)
     * @@return String
     * @@throws WEB3BaseException<BR>
     * @@roseuid 4119AD83011B
     */
    public String getPreference(
        String l_strInstitutionCode,
        String l_strName)
        throws WEB3BaseException;
    
    /**
     * (get�L�����A�敪)<BR>
     * �����o�H�敪����L�����A�敪���擾���A�l��ϊ�����B<BR> 
     * <BR>
     * @@param l_strOrderRootDiv - (�����o�H�敪) 
     * @@return String
     */
    public String getCareerDiv(String l_strOrderRootDiv);
}
@
