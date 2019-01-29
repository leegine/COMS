head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToTradeStopUpdConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�戵��~�ύX�m�F���N�G�X�g(WEB3AdminToTradeStopUpdConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/05 ���@@�F(���u) �V�K�쐬
                 : 2006/04/26 ����(���u) �d�l�ύX�E���f��No.062  
*/

package webbroker3.admintriggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OpenOtcDivDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�g���K�[�����Ǘ��ҁE�戵��~�ύX�m�F���N�G�X�g)<BR>
 * �g���K�[�����Ǘ��ҁE�戵��~�ύX�m�F���N�G�X�g�N���X<BR>
 * 
 * @@author ���@@�F
 * @@version 1.0
 */
public class WEB3AdminToTradeStopUpdConfirmRequest extends WEB3AdminToCommonRequest 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToTradeStopUpdConfirmRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_trade_stop_upd_confirm";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 20060403164900L;
    
    /**
     * (�戵��~���ꗗ)<BR>
     * �戵��~���ꗗ<BR>
     */
    public WEB3AdminToTradeStopInfoUnit[] tradeStopInfoList;
    
    /**
     * @@roseuid 4430D3B90222
     */
    public WEB3AdminToTradeStopUpdConfirmRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@super.validate()���R�[������B<BR>
     * <BR>
     * �Q�j�@@�戵��~���ꗗ�`�F�b�N<BR>
     * �@@�Q�|�P�j�@@this.�戵��~���ꗗ == null�̏ꍇ�A<BR>
     * �@@�@@�u�戵��~��񂪖����́v�̗�O���X���[����B<BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_02431<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@this.�戵��~���ꗗ�̗v�f�����A<BR>
     * �@@�@@�ȉ��̏�����Loop����B<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�P�j�@@�ύX��X�����J�敪�`�F�b�N<BR>
     * �@@�@@�@@this.�ύX��X�����J�敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�@@�@@�Q�|�Q�|�P�|�P�j�@@this.�ύX��X�����J�敪��<BR>
     * �@@�@@�@@�@@���L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�@@�@@�u�X�����J�敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�E"DEFAULT"�@@���I�[�N�V��������<BR>
     * �@@�@@�@@�@@�@@�E"�}�[�P�b�g���C�N����"<BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_02428<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�Q�j�@@�ύX���~���R�`�F�b�N<BR>
     * �@@�@@�@@this.�ύX���~���R != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�@@�@@�Q�|�Q�|�Q�|�P�j�@@this.�ύX���~���R��byte�� > 50byte�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�u���͗��R�G���[(��������)�v�̗�O���X���[����B<BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_01435<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�R�j�@@�ύX��L�������`�F�b�N<BR>
     * �@@�@@�@@�Q�|�Q�|�R�|�P�j�@@this.�ύX��L������To == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�u�L������To�����w��ł��B�v�̗�O���X���[����B<BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_01432<BR>
     * <BR>
     * �@@�@@�@@�Q�|�Q�|�R�|�Q�j�@@this.�ύX��L������To�����t�ɕϊ��ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�u�L������To�G���[(���݂��Ȃ����t)�v�̗�O���X���[����B<BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_01433<BR>
     * <BR>
     * �@@�@@�@@�Q�|�Q�|�R�|�R�j�@@this.�L������From > this.�ύX��L������To�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�u�L�������������G���[�v�̗�O���X���[����B<BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_01434<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�S�j�@@�戵��~���.validate()���R�[������B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4406BCD100CC
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // �P�jsuper.validate()���R�[������B
        super.validate();
        
        // �Q�j�戵��~���ꗗ�`�F�b�N
        // �@@�Q�|�P�jthis.�戵��~���ꗗ == null�̏ꍇ�A
        // �@@�@@     �u�戵��~��񂪖����́v�̗�O���X���[����B
        //           class : WEB3BusinessLayerException
        //           tag : BUSINESS_ERROR_02431
        if (this.tradeStopInfoList == null || this.tradeStopInfoList.length == 0)
        {
            log.debug("�戵��~��񂪖����͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02431,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�戵��~��񂪖����͂ł��B");            
        }
        
        // �Q�|�Q�j�@@this.�戵��~���ꗗ�̗v�f�����A�ȉ��̏�����Loop����B
        int l_intLength = this.tradeStopInfoList.length;  
        for (int i = 0; i < l_intLength; i++)
        {
            // �Q�|�Q�|�P�j�ύX��X�����J�敪�`�F�b�N
            // �@@�@@�@@     this.�ύX��X�����J�敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
            if (WEB3StringTypeUtility.isNotEmpty(tradeStopInfoList[i].aftOtcOpenDiv))
            {
                // �Q�|�Q�|�P�|�P�jthis.�ύX��X�����J�敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A
                // �@@�@@�@@�@@      �u�X�����J�敪������`�̒l�v�̗�O���X���[����B
                // �@@�@@�@@�@@�@@     �E"DEFAULT"�@@���I�[�N�V��������
                // �@@�@@�@@�@@�@@     �E"�}�[�P�b�g���C�N����"
                //               class : WEB3BusinessLayerException
                //               tag : BUSINESS_ERROR_02428
                if (!(WEB3OpenOtcDivDef.MARKET_MAKE_PRODUCT.equals(tradeStopInfoList[i].aftOtcOpenDiv)
                    || WEB3OpenOtcDivDef.DEFAULT.equals(tradeStopInfoList[i].aftOtcOpenDiv)))
                {
                    log.debug("�X�����J�敪�����݂��Ȃ��R�[�h�l�ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02428,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�X�����J�敪�����݂��Ȃ��R�[�h�l�ł��B");               
                }
            }
            
            // �Q�|�Q�|�Q�j�ύX���~���R�`�F�b�N
            // �@@�@@�@@      this.�ύX���~���R != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
            if (WEB3StringTypeUtility.isNotEmpty(tradeStopInfoList[i].aftChangeStopReason))
            {
                // �Q�|�Q�|�Q�|�P�j�@@this.�ύX���~���R��byte�� > 50byte�̏ꍇ�A
                // �@@�@@�@@�@@�u���͗��R�G���[(��������)�v�̗�O���X���[����B
                //     class : WEB3BusinessLayerException
                //     tag : BUSINESS_ERROR_01435
                if (tradeStopInfoList[i].aftChangeStopReason.getBytes().length > 50)
                {
                    log.debug("���͗��R�G���[(��������)�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01435,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���͗��R�G���[(��������)�B"); 
                 }                      
            }
            
            // �Q�|�Q�|�R�j�ύX��L�������`�F�b�N
            //�@@�@@�@@�Q�|�Q�|�R�|�P�jthis.�ύX��L������To == null�̏ꍇ�A
            // �@@�@@�@@�@@           �u�L������To�����w��ł��B�v�̗�O���X���[����B
            //                     class : WEB3BusinessLayerException
            //                     tag : BUSINESS_ERROR_01432
            if (WEB3StringTypeUtility.isEmpty(tradeStopInfoList[i].aftChangeExpirationEndDate))
            {
                log.debug("�L������To�����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01432,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�L������To�����w��ł��B");                
            }
            
            // �Q�|�Q�|�R�|�Q�jthis.�ύX��L������To�����t�ɕϊ��ł��Ȃ��ꍇ�A
            // �@@�@@�@@�@@      �u�L������To�G���[(���݂��Ȃ����t)�v�̗�O���X���[����B
            //               class : WEB3BusinessLayerException
            //               tag : BUSINESS_ERROR_01433
            if (!WEB3StringTypeUtility.isDateStr(tradeStopInfoList[i].aftChangeExpirationEndDate, "yyyyMMdd"))
            {
                log.debug("�L������To�G���[(���݂��Ȃ����t)�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01433,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�L������To�G���[(���݂��Ȃ����t)�B");                                 
            }
            
            // �Q�|�Q�|�R�|�R�jthis.�L������From > this.�ύX��L������To�̏ꍇ�A
            // �@@�@@�@@�@@       �u�L�������������G���[�v�̗�O���X���[����B
            //                class : WEB3BusinessLayerException
            //                tag : BUSINESS_ERROR_01434
            if (WEB3DateUtility.compare(
                WEB3DateUtility.getDate(tradeStopInfoList[i].expirationStartDate, "yyyyMMdd"),
                WEB3DateUtility.getDate(tradeStopInfoList[i].aftChangeExpirationEndDate, "yyyyMMdd")) > 0)
            {
                log.debug("�L�������������G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01434,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�L�������������G���[�B");                         
            }
            
            // �Q�|�Q�|�S�j�戵��~���.validate()���R�[������B
            tradeStopInfoList[i].validate();               
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (createResponse�̎���)<BR>
     * <BR>
     * �g���K�[�����Ǘ��ҁE�戵��~�ύX�m�F���X�|���X�I�u�W�F�N�g��ԋp����B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 41E78F6401A5
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminToTradeStopUpdConfirmResponse(this);
    }
}
@
