head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.07.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoDisplayContentsUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �\�����e���(WEB3PvInfoDisplayContentsUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2006/05/12 ������(���u) �d�l�ύX�Ǘ��䒠�E���f��No.062
Revesion History : 2006/05/22 ������(���u) �d�l�ύX�Ǘ��䒠�E���f��No.063
Revesion History : 2008/08/14 ���@@�g(���u) �d�l�ύX�Ǘ��䒠�E���f��No.105
*/
package webbroker3.pvinfo.message;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3PvInfoConditionDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�\�����e���)<BR>
 * �\�����e���N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3PvInfoDisplayContentsUnit extends Message 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3PvInfoDisplayContentsUnit.class);

    /**
     * (�\�����eID)<BR>
     * �\�����eID<BR>
     */
    public String displayContentsId;
    
    /**
     * (�\�������ԍ�)<BR>
     * �\�������ԍ�<BR>
     * <BR>
     * 0000�F�@@�_�C���N�g�w��<BR>
     * 1001�F�@@������������&�M�p�����J��<BR>
     * 1002�F�@@������������&�M�p�������J��<BR>
     * 1003�F�@@���֋�����<BR>
     * 1004�F�@@���֋�����<BR>
     * 1005�F�@@�؋����s��<BR>
     * 1006�F�@@���ϊ����ԋ߁i�ꃖ���O�j�̌��ʕۗL<BR>
     * 1007�F�@@���ϊ����ԋ߁i��T�ԑO�j�̌��ʕۗL<BR>
     * 1008�F�@@�M�p�����J��<BR>
     * 1009�F�@@�M�p�������J��<BR>
     * 1010�F�@@�I�v�V���������J��<BR>
     * 1011�F�@@�����ۗL<BR>
     * 1012�F�@@�M�p���ʕۗL<BR>
     * 1013�F�@@���M�ۗL<BR>
     * 1014�F�@@�ݓ��ۗL<BR>
     * 1015�F�@@�I�v�V�������ʕۗL<BR>
     * 1016�F�@@�~�j���ۗL<BR>
     * 1017�F�@@�敨�ۗL<BR>
     * 1018�F�@@�a����L��&�،��c����<BR>
     * 1019�F�@@�a�������&�،��c����<BR>
     * 1020�F�@@�����E�M�p���������i�����j<BR>
     * 1021�F�@@�����E�M�p���������i�����j<BR>
     * 1022�F�@@�����E�M�p��蔭��<BR>
     * 1023�F�@@�S�ڋq<BR>
     * 1024�F�@@���[���A�h���X���o�^<BR>
     * 1025�F�@@IPO���I<BR>
     * 1026�F�@@IPO�J�グ���I<BR>
     * 1027�F�@@�����~<BR>
     * 1028�F�@@���O�C���p�X���[�h�ύX�v<BR>
     * 1029�F�@@�O���،������J��<BR>
     * 1030�F �O���ۗL<BR>
     * 1031�F �O�����������i�����j<BR>
     * 1032�F �O�����������i�����j<BR>
     * 1033�F �O����蔭��<BR>
     * 1041�F�@@20������1����30������5���ȉ�<BR>
     * 1042�F�@@20������1����30������6��<BR>
     * 1043�F�@@20������2����30������6���ȉ�<BR>
     * 1044�F�@@20������3���ȏ�<BR>
     * 1045�F�@@30������2�`4��<BR>
     * 1046�F�@@30������5��<BR>
     * 1047�F�@@30������6��<BR>
     * 1048�F�@@30������7���ȏ�<BR>
     */
    public String conditionNumber;
    
    /**
     * (�D��敪)<BR>
     * �D��敪<BR>
     * <BR>
     * 0�F�@@�_�C���N�g�w��<BR>
     * 1�F�@@�ŗD��<BR>
     * 2�F�@@�D��<BR>
     * 3�F�@@�ʏ�<BR>
     */
    public String priorityDiv;
    
    /**
     * (�\������From)<BR>
     * �\������From<BR>
     */
    public String listStartDate;
    
    /**
     * (�\������To)<BR>
     * �\������To<BR>
     */
    public String listEndDate;
    
    /**
     * (�\���^�C�g��)<BR>
     * �\���^�C�g��<BR>
     */
    public String displayTitle;
    
    /**
     * (�\������)<BR>
     * �\������<BR>
     */
    public String displayMessage;
    
    /**
     * (�\���F)<BR>
     * �\���F<BR>
     * <BR>
     * 0�F�@@��<BR>
     * 1�F�@@��<BR>
     * 2�F�@@��<BR>
     * 3�F�@@��<BR>
     * 4�F�@@��<BR>
     */
    public String displayColor;
    
    /**
     * (�_�ŕ\���t���O)<BR>
     * �_�ŕ\���t���O<BR>
     * <BR>
     * false�F�@@�Ȃ�<BR>
     * true�F�@@����<BR>
     */
    public boolean blinkDisplayFlag;
    
    /**
     * (URL�w��)<BR>
     * URL�w��<BR>
     */
    public String displayUrl;
    
    /**
     * (�ŏI�X�V�����\���t���O)<BR>
     * �ŏI�X�V�����\���t���O<BR>
     * <BR>
     * false�F�@@�\��<BR>
     * true�F�@@��\��<BR>
     */
    public boolean lastUpdateTimeDisplayFlag;
    
    /**
     * (�L��/�����t���O)<BR>
     * �L��/�����t���O<BR>
     * <BR>
     * false�F�@@�L��<BR>
     * true�F�@@����<BR>
     */
    public boolean effectiveFlag;
    
    /**
     * (�\���}��)<BR>
     * �\���}��<BR>
     * <BR>
     * 0�F�@@DEFAULT(PC&���o�C��)<BR>
     * 1�F�@@PC<BR>
     * 2�F�@@���o�C��<BR>
     */
    public String displayDevice;
    
    /**
     * (�ŏI�X�V��)<BR>
     * �ŏI�X�V��<BR>
     */
    public String lastUpdateMember;
    
    /**
     * (�ŏI�X�V����)<BR>
     * �ŏI�X�V����<BR>
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (�\�����e���)<BR>
     * �R���X�g���N�^�B<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoDisplayContentsUnit
     * @@roseuid 414670CA01E6
     */
    public WEB3PvInfoDisplayContentsUnit() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�\�������ԍ��`�F�b�N<BR>
     * �@@this.�\�������ԍ� == null�̏ꍇ�A<BR>
     * �@@�u�\�������G���[�v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01044<BR>
     * <BR>
     * �Q�j�\���^�C�g���`�F�b�N<BR>
     * �@@this.�\���^�C�g�����ȉ��̏����̂��Âꂩ�ɊY������ꍇ�A<BR>
     * �@@�u�\���^�C�g���G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�Ethis.�\���^�C�g�� == null<BR>
     * �@@�@@�EWEB3StringTypeUtility.getFixedByteLength(this.�\���^�C�g��) > 100<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01045<BR>
     * <BR>
     * �R�j�\�����̓`�F�b�N<BR>
     * �@@this.�\�����͂��ȉ��̏����̂��Âꂩ�ɊY������ꍇ�A<BR>
     * �@@�u�\�����̓G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�Ethis.�\������ == null<BR>
     * �@@�@@�Ethis.�\������.byte�� > 2000byte<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01046<BR>
     * <BR>
     * �S�j�\���}�̃`�F�b�N<BR>
     * �@@this.�\���}�� == null�̏ꍇ�A<BR>
     * �@@�u�\���}�̃G���[�v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01047<BR>
     * <BR>
     * �T�j�\���D��x�`�F�b�N<BR>
     * �@@this.�D��敪���ȉ��̏����̂��Âꂩ�ɊY������ꍇ�A<BR>
     * �@@�u�\���D��x�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�Ethis.�\�������ԍ� == ("1001�F������������&�M�p�����J��" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"1002�F������������&�M�p�������J��" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"1003�F���֋�����" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"1005�F�؋����s��" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"1007�F�@@���ϊ����ԋ߁i��T�ԑO�j�̌��ʕۗL") ����<BR>
     * �@@�@@�@@this.�D��敪 != null<BR>
     * �@@�@@�Ethis.�\�������ԍ� != ("1001�F������������&�M�p�����J��" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"1002�F������������&�M�p�������J��" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"1003�F���֋�����" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"1005�F�؋����s��" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"1007�F�@@���ϊ����ԋ߁i��T�ԑO�j�̌��ʕۗL") ����<BR>
     * �@@�@@�@@this.�D��敪 == null<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01048<BR>
     * <BR>
     * �U�j�\������(��)�`�F�b�N<BR>
     * �@@this.�\������From != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���A<BR>
     * �@@���Âꂩ�̏����ɊY������ꍇ�́A<BR>
     * �@@�u�\������(��)�G���[�v�̗�O���X���[����B<BR>
     * �@@�EDate�^�ɕϊ����ăG���[������<BR>
     * �@@�E�\������From�̔N�������� <= �V�X�e������(*1)�̔N��������<BR>
     * ��<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01049<BR>
     * <BR>
     * �V�j�\������(��)�`�F�b�N<BR>
     * �@@this.�\������To != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���A<BR>
     * �@@���Âꂩ�̏����ɊY������ꍇ�́A<BR>
     * �@@�u�\������(��)�G���[�v�̗�O���X���[����B<BR>
     * �@@�EDate�^�ɕϊ����ăG���[������<BR>
     * �@@�E�\������To�̔N�������� <= �V�X�e������(*1)�̔N��������<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01050<BR>
     * <BR>
     * �W�j�\������(���`��)�������`�F�b�N<BR>
     * �@@this.�\������From != null ���� this.�\������To != null�̏ꍇ�A<BR>
     * �@@�ȉ��̏����ɊY������ꍇ�́A�u�\�����ԕs�����G���[�v�̗�O��<BR>
     * �@@�X���[����B<BR>
     * �@@�@@�Ethis.�\������From > this.�\������To<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01051<BR>
     * <BR>
     * �X�j�\���F�`�F�b�N<BR>
     * �@@this.�\���F���ȉ��̏����̂��Âꂩ�ɊY������ꍇ�A<BR>
     * �@@�u�\���F�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�Ethis.�\�������ԍ� == ("0000�F�_�C���N�g�w��" or<BR>
                                   "1001�F������������&�M�p�����J��" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"1002�F������������&�M�p�������J��" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"1003�F���֋�����" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"1005�F�؋����s��" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"1007�F�@@���ϊ����ԋ߁i��T�ԑO�j�̌��ʕۗL") ����<BR>
     * �@@�@@�@@this.�\���F != null<BR>
     * �@@�@@�Ethis.�\�������ԍ� != ("0000�F�_�C���N�g�w��" or<BR>
                                   "1001�F������������&�M�p�����J��" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"1002�F������������&�M�p�������J��" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"1003�F���֋�����" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"1005�F�؋����s��" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"1007�F�@@���ϊ����ԋ߁i��T�ԑO�j�̌��ʕۗL") ����<BR>
     * �@@�@@�@@this.�\���F == null<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01052<BR>
     * <BR>
     * �P�O�jURL�`�F�b�N<BR>
     * �@@this.URL�w�肪�ȉ��̏����̂��Âꂩ�ɊY������ꍇ�A<BR>
     * �@@�uURL�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�Ethis.�\�������ԍ� == ("1001:������������&�M�p�����J��" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"1002:������������&�M�p�������J��" or<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"1003:���֋�����") ����<BR>
     * �@@�@@�@@this.URL�w�� != null<BR>
     * �@@�@@�Ethis.URL�w��.length > 100<BR>
     * �@@�@@�Ethis.URL�w��̐擪�̕�����"http"�łȂ�<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01053<BR>
     * <BR>
     * (*1)<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG)�ɂ�<BR>
     * �擾�����V�X�e������<BR>
     * @@roseuid 415C02E900A5
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�\�������ԍ��`�F�b�N 
        if(conditionNumber == null)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01044.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01044,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�Q�j�\���^�C�g���`�F�b�N
        if (displayTitle == null || WEB3StringTypeUtility.getFixedByteLength(displayTitle) > 100)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01045.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01045,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�R�j�\�����̓`�F�b�N
        if (displayMessage == null || 
            displayMessage != null && WEB3StringTypeUtility.getByteLength(displayMessage) > 2000)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01045.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01046,
                getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        //�S�j�\���}�̃`�F�b�N
        if(displayDevice == null)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01047.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01047,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�T�j�\���D��x�`�F�b�N
        if(priorityDiv == null &&
            (!WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_OPEN.equals(conditionNumber) &&
            !WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_CLOSE.equals(conditionNumber) &&
            !WEB3PvInfoConditionDef.ADVANCE_GENERATION.equals(conditionNumber) &&
            !WEB3PvInfoConditionDef.IFO_DEPOSIT_SHORTAGE.equals(conditionNumber) &&
            !WEB3PvInfoConditionDef.SETTLE_BEF_AWEEK_CONTRACT_HAS.equals(conditionNumber)) 
            || priorityDiv != null &&
            (WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_OPEN.equals(conditionNumber) ||
            WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_CLOSE.equals(conditionNumber) ||
            WEB3PvInfoConditionDef.ADVANCE_GENERATION.equals(conditionNumber) ||
            WEB3PvInfoConditionDef.IFO_DEPOSIT_SHORTAGE.equals(conditionNumber) ||
            WEB3PvInfoConditionDef.SETTLE_BEF_AWEEK_CONTRACT_HAS.equals(conditionNumber)))
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01048.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01048,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�U�j�\������(��)�`�F�b�N
        Timestamp l_tsCurrent = (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        //�EDate�^�ɕϊ����ăG���[������
        Date l_dStartDate = WEB3DateUtility.getDate(listStartDate, "yyyyMMddHHmm"); 
        if(listStartDate != null && l_dStartDate == null)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01049.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01049,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        if(listStartDate != null && WEB3DateUtility.compareToMinute(l_dStartDate, l_tsCurrent) <= 0)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01049.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01049,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�V�j�\������(��)�`�F�b�N
        //�EDate�^�ɕϊ����ăG���[������
        Date l_dEndDate = WEB3DateUtility.getDate(listEndDate, "yyyyMMddHHmm");
        if(listEndDate != null && l_dEndDate == null)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01050.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01050,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        if(listEndDate != null && WEB3DateUtility.compareToMinute(l_dEndDate, l_tsCurrent) <= 0)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01050.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01050,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�W�j�\������(���`��)�������`�F�b�N
        if(listStartDate != null && listEndDate != null)
        {
            if(WEB3DateUtility.compare(l_dStartDate, l_dEndDate) > 0)
            {
                log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01051.error_message);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01051,
                    getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        //�X�j�\���F�`�F�b�N
        if(displayColor == null &&
            (!WEB3PvInfoConditionDef.DIRECT_ASSIGN.equals(conditionNumber) &&
            !WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_OPEN.equals(conditionNumber) &&
            !WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_CLOSE.equals(conditionNumber) &&
            !WEB3PvInfoConditionDef.ADVANCE_GENERATION.equals(conditionNumber) &&
            !WEB3PvInfoConditionDef.IFO_DEPOSIT_SHORTAGE.equals(conditionNumber) &&
            !WEB3PvInfoConditionDef.SETTLE_BEF_AWEEK_CONTRACT_HAS.equals(conditionNumber)) 
            || displayColor != null &&
            (WEB3PvInfoConditionDef.DIRECT_ASSIGN.equals(conditionNumber) ||
            WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_OPEN.equals(conditionNumber)||
            WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_CLOSE.equals(conditionNumber)||
            WEB3PvInfoConditionDef.ADVANCE_GENERATION.equals(conditionNumber)||
            WEB3PvInfoConditionDef.IFO_DEPOSIT_SHORTAGE.equals(conditionNumber)||
            WEB3PvInfoConditionDef.SETTLE_BEF_AWEEK_CONTRACT_HAS.equals(conditionNumber)))
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01052.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01052,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�O�jURL�`�F�b�N
        if(displayUrl != null && (WEB3PvInfoConditionDef.ADVANCE_GENERATION.equals(conditionNumber) 
            || WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_OPEN.equals(conditionNumber) 
            || WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_CLOSE.equals(conditionNumber))
            || displayUrl != null && (WEB3StringTypeUtility.getByteLength(displayUrl) > 100)
            || displayUrl != null && (!displayUrl.toLowerCase().startsWith("http")))
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01053.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01053,
                getClass().getName() + "." + STR_METHOD_NAME);
        }     

        log.exiting(STR_METHOD_NAME);
    }
}
@
