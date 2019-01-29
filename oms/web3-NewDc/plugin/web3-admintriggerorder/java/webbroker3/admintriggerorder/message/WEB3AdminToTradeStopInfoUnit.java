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
filename	WEB3AdminToTradeStopInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �戵��~���(WEB3AdminToTradeStopInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04 ���@@�F(���u) �V�K�쐬
                 : 2006/04/06 ���_�O(���u) �d�l�ύX�E���f��056
                 : 2006/04/26 ����(���u) �d�l�ύX�E���f��No.061�A062                  
*/

package webbroker3.admintriggerorder.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OpenOtcDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�戵��~���)<BR>
 * �戵��~���N���X<BR>
 * 
 * @@author ���@@�F
 * @@version 1.0
 */
public class WEB3AdminToTradeStopInfoUnit extends Message 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToTradeStopInfoUnit.class);
    
    /**
     * (ID)<BR>
     * ���ꎷ�s�����戵��~ID<BR>
     * <BR>
     * ���o�^�������̂�null�B<BR>
     */
    public String id = null;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * <BR>
     * ���u���i�ʁv�̏ꍇ�A�ݒ�Ώۂ̕��X�R�[�h���Z�b�g�B<BR>
     * �@@�ȊO�A"000"���Z�b�g�B<BR>
     */
    public String branchCode;
    
    /**
     * (���i�敪)<BR>
     * ���i�敪 <BR>
     * <BR>
     * 1�F�@@��������<BR>
     * 2�F�@@�M�p���<BR>
     * 3�F�@@�敨<BR>
     * 4�F�@@�I�v�V���� <BR>
     * <BR>
     * ���u���i�ʁv�̏ꍇ�A�ݒ�Ώۂ̏��i�敪���Z�b�g�B<BR>
     * �@@�ȊO�Anull���Z�b�g�B<BR>
     */
    public String productDiv = null;
    
    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * <BR>
     * ���u�s��ʁv�̏ꍇ�A�ݒ�Ώۂ̎s��R�[�h���Z�b�g�B<BR>
     * �@@�ȊO�Anull���Z�b�g�B<BR>
     */
    public String marketCode = null;
    
    /**
     * (�X�����J�敪)<BR>
     * �X�����J�敪<BR>
     * <BR>
     * 0�F�@@DEFAULT�@@���I�[�N�V��������<BR>
     * 3�F�@@�}�[�P�b�g���C�N����<BR>
     * null�F�@@����<BR>
     * <BR>
     * ���s��R�[�h == "JASDAQ"�̏ꍇ�̂ݗL���B<BR>
     */
    public String otcOpenDiv = null;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * <BR>
     * ���u�����ʁv�̏ꍇ�A�ݒ�Ώۂ̖����R�[�h���Z�b�g�B<BR>
     * �@@�ȊO�Anull���Z�b�g�B<BR>
     */
    public String productCode = null;
    
    /**
     * (������)<BR>
     * ������<BR>
     * <BR>
     * ���u�����ʁv�̏ꍇ�A�����R�[�h�ɊY��������������Z�b�g�B<BR>
     * �@@�ȊO�Anull���Z�b�g�B<BR>
     */
    public String productName = null;
    
    /**
     * (��~���R)<BR>
     * ��~���R<BR>
     */
    public String stopReason = null;
    
    /**
     * (�L������From)<BR>
     * �L������From<BR>
     * �iYYYYMMDD�j<BR>
     * <BR>
     * ���������̏ꍇ�́A00010101���Z�b�g�B<BR>
     * �@@�i���i�ʁA�s��ʂ̏ꍇ����L�l���Z�b�g�B�j<BR>
     */
    public String expirationStartDate;
    
    /**
     * (�L������To)<BR>
     * �L������To<BR>
     * �iYYYYMMDD�j<BR>
     * <BR>
     * ���������̏ꍇ�́A99991231���Z�b�g�B<BR>
     * �@@�i���i�ʁA�s��ʂ̏ꍇ����L�l���Z�b�g�B�j<BR>
     */
    public String expirationEndDate;
    
    /**
     * (�ύX��X�����J�敪)<BR>
     * �ύX��X�����J�敪<BR>
     * <BR>
     * 0�F�@@DEFAULT�@@���I�[�N�V��������<BR>
     * 3�F�@@�}�[�P�b�g���C�N����<BR>
     * null�F�@@����<BR>
     * <BR>
     * ���s��R�[�h == "JASDAQ"�̏ꍇ�̂ݗL���B<BR>
     */
    public String aftOtcOpenDiv = null;
    
    /**
     * (�ύX���~���R)<BR>
     * �ύX���~���R<BR>
     * <BR>
     * ���ύX���͌�̒�~���R���Z�b�g����B<BR>
     * �@@�ύX�Ȃ��̏ꍇ�́Athis.��~���R�Ɠ����l���Z�b�g����B<BR>
     */
    public String aftChangeStopReason = null;
    
    /**
     * (�ύX��L������To)<BR>
     * �ύX��L������To<BR>
     * �iYYYYMMDD�j<BR>
     * <BR>
     * ���ύX���͌�̗L������To���Z�b�g����B<BR>
     * �@@�ύX�Ȃ��̏ꍇ�́Athis.�L������To�Ɠ����l���Z�b�g����B<BR>
     */
    public String aftChangeExpirationEndDate = null;
    
    /**
     * (������~�󋵈ꗗ)<BR>
     * ������~�󋵈ꗗ<BR>
     */
    public WEB3AdminToOrderStopStateUnit[] orderStopStateList;
    
    /**
     * (�戵��~���)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 4406AFCB02EE
     */
    public WEB3AdminToTradeStopInfoUnit() 
    {
     
    }
    
    /**
     * ���N���X�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@���X�R�[�h�`�F�b�N <BR>
     * �@@�P�|�P�j�@@this.���X�R�[�h == null�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u���X�R�[�h��null�v�̗�O���X���[����B <BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_02174<BR>
     * <BR>
     * �@@�P�|�Q�j�@@this.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�@@�E���X�R�[�h != ���� <BR>
     * �@@�@@�@@�@@�@@�E���X�R�[�h.length != 3 <BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_00779<BR>
     * <BR>
     * �Q�j�@@���i�敪�`�F�b�N<BR>
     * �@@this.���i�敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�Q�|�P�j�@@this.���i�敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A <BR>
     * �@@�@@�u���i�敪������`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�E"��������" <BR>
     * �@@�@@�@@�@@�E"�M�p���" <BR>
     * �@@�@@�@@�@@�E"�敨"<BR>
     * �@@�@@�@@�@@�E"�I�v�V����" <BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_01068<BR>
     * <BR>
     * �R�j�@@�s��R�[�h�`�F�b�N<BR>
     * �@@this.�s��R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�R�|�P�j�@@�@@this.�s��R�[�h�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�E"����" <BR>
     * �@@�@@�@@�E"���" <BR>
     * �@@�@@�@@�E"���É�" <BR>
     * �@@�@@�@@�E"����" <BR>
     * �@@�@@�@@�E"�D�y" <BR>
     * �@@�@@�@@�E"NNM" <BR>
     * �@@�@@�@@�E"JASDAQ" <BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_00608<BR>
     * <BR>
     * �S�j�@@�X�����J�敪�`�F�b�N<BR>
     * �@@this.�X�����J�敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�S�|�P�j�@@this.�X�����J�敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�u�X�����J�敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E"�o�^"�@@���I�[�N�V��������<BR>
     * �@@�@@�@@�E"�}�[�P�b�g���C�N����"<BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_02428<BR>
     * <BR>
     * �T�j�@@�����R�[�h�`�F�b�N <BR>
     * �@@this.�����R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * �@@�T�|�P�j�@@this.�����R�[�h���ȉ��̏����ɊY������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�����R�[�h�G���[�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�E�����R�[�h != ���� <BR>
     * �@@�@@�@@�@@�E�����R�[�h.length != 5 <BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_01067<BR>
     * <BR>
     * �U�j�@@��~���R�`�F�b�N<BR>
     * �@@this.��~���R != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�U�|�P�j�@@this.��~���R��byte�� > 50byte�̏ꍇ�A<BR>
     * �@@�@@�u���͗��R�G���[(��������)�v�̗�O���X���[����B<BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_01435<BR>
     * <BR>
     * �V�j�@@�L�������`�F�b�N<BR>
     * �@@�V�|�P�j�@@this.�L������From == null�̏ꍇ�A<BR>
     * �@@�@@�u�L������From�����w��ł��B�v�̗�O���X���[����B<BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_01430<BR>
     * <BR>
     * �@@�V�|�Q�j�@@this.�L������From�����t�ɕϊ��ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�u�L������From�G���[(���݂��Ȃ����t)�v�̗�O���X���[����B<BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_01431<BR>
     * <BR>
     * �@@�V�|�R�j�@@this.�L������To == null�̏ꍇ�A<BR>
     * �@@�@@�u�L������To�����w��ł��B�v�̗�O���X���[����B<BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_01432<BR>
     * <BR>
     * �@@�V�|�S�j�@@this.�L������To�����t�ɕϊ��ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�u�L������To�G���[(���݂��Ȃ����t)�v�̗�O���X���[����B<BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_01433<BR>
     * <BR>
     * �@@�V�|�T�j�@@this.�L������From > this.�L������To�̏ꍇ�A<BR>
     * �@@�@@�u�L�������������G���[�v�̗�O���X���[����B<BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_01434<BR>
     * <BR>
     * �W�j�@@������~�󋵈ꗗ�`�F�b�N<BR>
     * �@@�W�|�P�j�@@this.������~�󋵈ꗗ == null�̏ꍇ�A<BR>
     * �@@�@@�u������~�󋵂������́v�̗�O���X���[����B<BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_02429<BR>
     * <BR>
     * �@@�W�|�Q�j�@@this.������~�󋵈ꗗ�̗v�f�����A<BR>
     * �@@�@@�ȉ��̏�����Loop����B<BR>
     * �@@�@@�W�|�Q�|�P�j�@@������~��.validate()���R�[������B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 441135FE03B7
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // �P�j���X�R�[�h�`�F�b�N 
        // �@@�P�|�P�jthis.���X�R�[�h == null�̏ꍇ�A�u���X�R�[�h��null�v�̗�O���X���[����B 
        //          class : WEB3BusinessLayerException
        //          tag : BUSINESS_ERROR_02174
        if (WEB3StringTypeUtility.isEmpty(this.branchCode))
        {
            log.debug("���X�R�[�h��null�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02174,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h��null�ł��B");
        }
        
        //  �P�|�Q�jthis.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A�u���X�R�[�h�G���[�v�̗�O���X���[����B 
        // �@@�@@�@@�@@�@@�E���X�R�[�h != ���� 
        // �@@�@@�@@�@@�@@�E���X�R�[�h.length != 3 
        //           class : WEB3BusinessLayerException
        //           tag : BUSINESS_ERROR_00779
        if (!WEB3StringTypeUtility.isDigit(this.branchCode)
            || this.branchCode.length() != 3)
        {
            log.debug("���X�R�[�h�̓��͂��s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�̓��͂��s���ł��B");            
        }
        
        // �Q�j���i�敪�`�F�b�N
        // �@@  this.���i�敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (WEB3StringTypeUtility.isNotEmpty(this.productDiv))
        {
            //�Q�|�P�jthis.���i�敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A
            //�@@�@@�@@�u���i�敪������`�̒l�v�̗�O���X���[����B
            //�@@�@@�@@   �E"��������" 
            // �@@�@@�@@�@@�E"�M�p���" 
            // �@@�@@�@@�@@�E"�敨"
            // �@@�@@�@@�@@�E"�I�v�V����" 
            //         class : WEB3BusinessLayerException
            //         tag : BUSINESS_ERROR_01068
            
            if (!(WEB3CommodityDivDef.EQUITY.equals(this.productDiv)
                || WEB3CommodityDivDef.MARGIN.equals(this.productDiv)
                || WEB3CommodityDivDef.FUTURE.equals(this.productDiv)
                || WEB3CommodityDivDef.OPTION.equals(this.productDiv)))
            {
                log.debug("���i�敪�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01068,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���i�敪�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }
        
        // �R�j�s��R�[�h�`�F�b�N
        // �@@  this.�s��R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (WEB3StringTypeUtility.isNotEmpty(this.marketCode))
        {            
            // �R�|�P�jthis.�s��R�[�h�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A
            // �@@�@@     �u�s��R�[�h������`�̒l�v�̗�O���X���[����B 
            // �@@�@@�@@    �E"����" 
            // �@@�@@�@@    �E"���" 
            // �@@�@@�@@    �E"���É�" 
            // �@@�@@�@@    �E"����" 
            // �@@�@@�@@    �E"�D�y" 
            // �@@�@@�@@    �E"NNM" 
            // �@@�@@�@@    �E"JASDAQ" 
            //          class : WEB3BusinessLayerException
            //          tag : BUSINESS_ERROR_00608
            if (!(WEB3MarketCodeDef.TOKYO.equals(this.marketCode)
                || WEB3MarketCodeDef.OSAKA.equals(this.marketCode)
                || WEB3MarketCodeDef.NAGOYA.equals(this.marketCode)
                || WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode)
                || WEB3MarketCodeDef.SAPPORO.equals(this.marketCode)
                || WEB3MarketCodeDef.NNM.equals(this.marketCode)
                || WEB3MarketCodeDef.JASDAQ.equals(this.marketCode)))
            {
                log.debug("�s��R�[�h�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�s��R�[�h�����݂��Ȃ��R�[�h�l�ł��B");
            }            
        }
        
        // �S�j�X�����J�敪�`�F�b�N
        //�@@   this.�X�����J�敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (WEB3StringTypeUtility.isNotEmpty(this.otcOpenDiv))
        {
            // �@@�S�|�P�jthis.�X�����J�敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A
            // �@@�@@     �u�X�����J�敪������`�̒l�v�̗�O���X���[����B
            // �@@�@@�@@    �E"DEFAULT"�@@���I�[�N�V��������
            // �@@�@@�@@    �E"�}�[�P�b�g���C�N����"
            //           class : WEB3BusinessLayerException
            //           tag : BUSINESS_ERROR_02428
            if (!(WEB3OpenOtcDivDef.MARKET_MAKE_PRODUCT.equals(this.otcOpenDiv)
                || WEB3OpenOtcDivDef.DEFAULT.equals(this.otcOpenDiv)))
            {
                log.debug("�X�����J�敪�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02428,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�X�����J�敪�����݂��Ȃ��R�[�h�l�ł��B");               
            }
        }
        
        // �T�j�����R�[�h�`�F�b�N 
        // �@@  this.�����R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (WEB3StringTypeUtility.isNotEmpty(this.productCode))
        {
            //�T�|�P�jthis.�����R�[�h���ȉ��̏����ɊY������ꍇ�A 
            // �@@�@@�@@ �����R�[�h�G���[�v�̗�O���X���[����B 
            // �@@�@@�@@ �E�����R�[�h != ���� 
            // �@@�@@�@@ �E�����R�[�h.length != 5
            //        class : WEB3BusinessLayerException
            //        tag : BUSINESS_ERROR_01067
            if (!WEB3StringTypeUtility.isDigit(this.productCode)
                || this.productCode.length() != 5)
            {
                log.debug("�����R�[�h�̓��͂��s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����R�[�h�̓��͂��s���ł��B");                
            }            
        }
        
        // �U�j��~���R�`�F�b�N
        // �@@this.��~���R != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (WEB3StringTypeUtility.isNotEmpty(this.stopReason))
        {
            //�U�|�P�jthis.��~���R��byte�� > 50byte�̏ꍇ�A
            // �@@�@@  �u���͗��R�G���[(��������)�v�̗�O���X���[����B
            //        class : WEB3BusinessLayerException
            //        tag : BUSINESS_ERROR_01435
            if (this.stopReason.getBytes().length > 50)
            {
                log.debug("���͗��R�G���[(��������)�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01435,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���͗��R�G���[(��������)�B");   
            }           
        }
        
        // �V�j�L�������`�F�b�N
        // �@@�V�|�P�jthis.�L������From == null�̏ꍇ�A
        // �@@�@@     �u�L������From�����w��ł��B�v�̗�O���X���[����B
        //           class : WEB3BusinessLayerException
        //          tag : BUSINESS_ERROR_01430
        if (WEB3StringTypeUtility.isEmpty(this.expirationStartDate))
        {
            log.debug("�L������From�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01430,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�L������From�����w��ł��B"); 
        }
        
        // �@@�V�|�Q�jthis.�L������From�����t�ɕϊ��ł��Ȃ��ꍇ�A
        // �@@�@@     �u�L������From�G���[(���݂��Ȃ����t)�v�̗�O���X���[����B
        //           class : WEB3BusinessLayerException
        //           tag : BUSINESS_ERROR_01431
        if (!WEB3StringTypeUtility.isDateStr(this.expirationStartDate, "yyyyMMdd"))
        {
            log.debug("�L������From�G���[(���݂��Ȃ����t)�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01431,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�L������From�G���[(���݂��Ȃ����t)�B"); 
        }
        
        //�@@�V�|�R�jthis.�L������To == null�̏ꍇ�A
        // �@@�@@    �u�L������To�����w��ł��B�v�̗�O���X���[����B
        //          class : WEB3BusinessLayerException
        //          tag : BUSINESS_ERROR_01432
        if (WEB3StringTypeUtility.isEmpty(this.expirationEndDate))
        {
            log.debug("�L������To�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01432,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�L������To�����w��ł��B"); 
        }
        
        // �@@�V�|�S�jthis.�L������To�����t�ɕϊ��ł��Ȃ��ꍇ�A
        // �@@�@@     �u�L������To�G���[(���݂��Ȃ����t)�v�̗�O���X���[����B
        //           class : WEB3BusinessLayerException
        //           tag : BUSINESS_ERROR_01433
        if (!WEB3StringTypeUtility.isDateStr(this.expirationEndDate, "yyyyMMdd"))
        {
            log.debug("�L������To�G���[(���݂��Ȃ����t)�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01433,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�L������To�G���[(���݂��Ȃ����t)�B"); 
        }
        
        // �@@�V�|�T�jthis.�L������From > this.�L������To�̏ꍇ�A
        // �@@�@@    �u�L�������������G���[�v�̗�O���X���[����B
        //          class : WEB3BusinessLayerException
        //          tag : BUSINESS_ERROR_01434
        if (WEB3DateUtility.compare(
            WEB3DateUtility.getDate(this.expirationStartDate, "yyyyMMdd"),
            WEB3DateUtility.getDate(this.expirationEndDate, "yyyyMMdd")) > 0)
        {
            log.debug("�L�������������G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01434,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�L�������������G���[�B");             
        }
        
        // �W�j�@@������~�󋵈ꗗ�`�F�b�N
        // �@@�W�|�P�jthis.������~�󋵈ꗗ == null�̏ꍇ�A
        // �@@�@@     �u������~�󋵂������́v�̗�O���X���[����B
        //           class : WEB3BusinessLayerException
        //           tag : BUSINESS_ERROR_02429
        if (this.orderStopStateList == null || this.orderStopStateList.length == 0)
        {
            log.debug("������~�󋵂������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02429,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "������~�󋵂������͂ł��B"); 
        }
        
        // �@@�W�|�Q�jthis.������~�󋵈ꗗ�̗v�f�����A�ȉ��̏�����Loop����B
        //�@@�@@�W�|�Q�|�P�j������~��.validate()���R�[������B
        int l_intLength = this.orderStopStateList.length;        
        for (int i = 0; i < l_intLength; i++)
        {
            this.orderStopStateList[i].validate();            
        }
        
        log.exiting(STR_METHOD_NAME);            
    }
}
@
