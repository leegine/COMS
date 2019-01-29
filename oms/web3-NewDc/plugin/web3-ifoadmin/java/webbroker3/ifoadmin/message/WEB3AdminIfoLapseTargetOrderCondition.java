head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoLapseTargetOrderCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�����Ώے�������(WEB3AdminIfoLapseTargetOrderCondition.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30�@@�Ӑ�(���u) �V�K�쐬
*/

package webbroker3.ifoadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�敨OP�����Ώے�������)<BR>
 * �敨OP�����Ώے�������<BR>
 * <BR>
 * @@author �Ӑ�(���u)
 * @@version 1.0
 */

public class WEB3AdminIfoLapseTargetOrderCondition extends Message 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoLapseTargetOrderCondition.class);
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h�̔z�� <BR>
     * <BR>
     * �����X�R�[�h�����͎��́APR�w�ŕێ����Ă��� <BR>
     * �@@�戵�\���X�R�[�h�ꗗ���Z�b�g�����B<BR>
     */
    public String[] branchCode;

    /**
     * (�敨�^�I�v�V�����敪)<BR>
     * �敨�^�I�v�V�����敪<BR>
     * <BR>
     * 1�F�@@�敨<BR>
     * 2�F�@@�I�v�V����<BR>
     * �������ʎw��̏ꍇ�̂ݓ���<BR>
     */
    public String fuOpDiv = null;

    /**
     * (�w�����)<BR>
     * �w�����<BR>
     * 0005�F�@@TOPIX<BR>
     * 0018�F�@@���o225<BR>
     * 0016�F�@@���o300<BR>
     * 0019�F�@@�~�j���o225<BR>
     * �������ʎw��̏ꍇ�̂ݓ���<BR>
     */
    public String targetProductCode = null;

    /**
     * (����)<BR>
     * yyyyMM�`��<BR>
     * �������ʎw��̏ꍇ�̂ݓ���<BR>
     */
    public String delivaryMonth = null;

    /**
     * (�s�g���i)<BR>
     * �������ʎw��̏ꍇ�̂ݓ���<BR>
     */
    public String strikePrice = null;

    /**
     * (�I�v�V�������i�敪)<BR>
     * P�F�@@�v�b�g<BR>
     * C�F�@@�R�[��<BR>
     * �������ʎw��̏ꍇ�̂ݓ���<BR>
     */
    public String opProductType = null;

    /**
     * (����敪�ꗗ)<BR>
     * ����敪�̈ꗗ<BR>
     * <BR>
     *601�F�@@�敨�V�K��������<BR>
     *602�F�@@�敨�V�K��������<BR>
     *603�F�@@�敨�������ԍϒ���<BR>
     *604�F�@@�敨�������ԍϒ���<BR>
     *605�F�@@OP�V�K��������<BR>
     *606�F�@@OP�V�K��������<BR>
     *607�F�@@OP�������ԍϒ���<BR>
     *608�F�@@OP�������ԍϒ���<BR>
     */
    public String[] tradingTypeList;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     */
    public String accountCode = null;

    /**
     * (�敨OP�����Ώے�������)<BR>
     * �R���X�g���N�^
     * @@return WEB3AdminIfoLapseTargetOrderCondition
     * @@roseuid 4469235C0148
     */
    public WEB3AdminIfoLapseTargetOrderCondition() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     *�P�j�@@���X�R�[�h�`�F�b�N<BR>
     *�P�|�P�j�@@this.���X�R�[�h == null�̏ꍇ�A<BR>
     *�@@�@@�@@�u���X�R�[�h��null�v�̗�O���X���[����B<BR>
     *<BR>
     *�P�|�Q�j�@@this.���X�R�[�h�̗v�f�����ȉ��̏������s���B<BR>
     *    �P�|�Q�|�P�j�@@this.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A<BR>
     *�@@�@@�@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B<BR>
     *�@@�@@�@@�@@�@@�E���X�R�[�h != ���� <BR>
     *�@@�@@�@@�@@�@@�E���X�R�[�h.length != 3 <BR>
     *<BR>
     *�Q�j�@@�敨�^�I�v�V�����敪�`�F�b�N<BR>
     *   this.�敨�^�I�v�V�����敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     *   �Q�|�P�j�@@this.�敨�^�I�v�V�����敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     *   �u�敨�^�I�v�V�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��v�̗�O���X���[����B<BR>
     *   �E"�敨" <BR>
     *   �E"�I�v�V����" <BR>
     *<BR>
     *�R�j�@@�w����ʃ`�F�b�N<BR>
     *   this.�w����� != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     *   �R�|�P�j�@@this.�w����� != �����̏ꍇ�A  <BR>
     *       �u�w����ʂ������ȊO�v�̗�O���X���[����B<BR>
     *<BR>
     *   �R�|�Q�j�@@this.�w����� != 4���̏ꍇ�A<BR>
     *     �u�w����ʌ����G���[�v�̗�O���X���[����B<BR>
     *<BR>
     *�S�j�@@�����`�F�b�N<BR>
     *   this.���� != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     *   �S�|�P�j�@@this.���� != �����̏ꍇ�A<BR>
     *       �u�����������ȊO�v�̗�O���X���[����B<BR>
     *<BR>
     *   �S�|�Q�j�@@this.���� != YYYYMM�`���̒l�ł������ꍇ�A<BR>
     *       �u�������t�`���G���[�v�̗�O���X���[����B<BR>
     *<BR>
     *�T�j�@@�s�g���i�`�F�b�N<BR>
     *   this.�s�g���i != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     *   �T�|�P�j�@@this.�s�g���i != �����̏ꍇ�A <BR>
     *       �u�s�g���i�������ȊO�v�̗�O���X���[����B<BR>
     *<BR>
     *   �T�|�Q�j�@@this.�s�g���i <= 0 �̏ꍇ�A<BR>
     *       �u�s�g���i��0�ȉ��v�̗�O���X���[����B<BR>
     *<BR>
     *   �T�|�R�j�@@this.�s�g���i > 8���̒l�ł������ꍇ�A<BR>
     *       �u�s�g���i�����G���[�v�̗�O���X���[����B<BR>
     *<BR>
     *�U�j�@@�I�v�V�������i�敪�`�F�b�N<BR>
     *   this.�I�v�V�������i�敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     *   �U�|�P�j�@@this.�I�v�V�������i�敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     *       �u�I�v�V�������i�敪������`�̒l�v�̗�O���X���[����B<BR>
     *       �E"�v�b�g"<BR>
     *       �E"�R�[��"<BR>
     *<BR>
     *�V�j�@@�����w��`�F�b�N<BR>
     *   �V�|�P�j�@@�������������͂���Ă���ꍇ�A<BR>
     *       (this.�敨�^�I�v�V�����敪 != null or<BR>
     *       this.�w����� != null or<BR>
     *       this.���� != null or<BR>
     *       this.�s�g���i != null or<BR>
     *       this.�I�v�V�������i�敪 != null)<BR>
     *   �ȉ��̏����̂�����ɂ��Y�����Ȃ���΁A�u�����w��G���[�v�̗�O���X���[����B<BR>
     *<BR>
     *   �@@�������������i�敨�j<BR>
     *       this.�敨�^�I�v�V�����敪 == "�敨" ����<BR>
     *       this.�w����� != null ����<BR>
     *       this.���� != null ����<BR>
     *       this.�s�g���i == null ���� <BR>
     *       this.�I�v�V�������i�敪 == null<BR>
     *<BR>
     *   �A�������������i�I�v�V�����j<BR>
     *       this.�敨�^�I�v�V�����敪 == "�I�v�V����" ����<BR>
     *       this.�w����� != null ����<BR>
     *       this.���� != null ����<BR>
     *       this.�s�g���i != null ����<BR>
     *       this.�I�v�V�������i�敪 != null<BR>
     *<BR>
     * �W�j�@@����敪�ꗗ�`�F�b�N<BR>
     *   �W�|�P�j�@@this.����敪�ꗗ == null�ł���΁A<BR>
     *        �u����敪�����w��ł��B�v�̗�O���X���[����B<BR>
     *<BR>
     *   �W�|�Q�j�@@this.����敪�ꗗ�ɉ��L�̍��ڈȊO���ݒ� <BR>
     *       ����Ă�����A�u����敪�����݂��Ȃ��R�[�h�l�ł��B�v�� <BR>
     *       ��O���X���[����B<BR>
     *       �E"�敨�V�K��������"<BR>
     *       �E"�敨�V�K��������" <BR>
     *       �E"�敨�������ԍϒ���" <BR>
     *       �E"�敨�������ԍϒ���" <BR>
     *       �E"OP�V�K��������" <BR>
     *       �E"OP�V�K��������" <BR>
     *       �E"OP�������ԍϒ���" <BR>
     *       �E"OP�������ԍϒ���" <BR>
     *<BR>
     *�X�j�@@�ڋq�R�[�h�`�F�b�N<BR>
     *   this.�ڋq�R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     *   �X�|�P�j�@@this.�ڋq�R�[�h���ȉ��̏����ɊY������ꍇ�A<BR>
     *       �u�ڋq�R�[�h�G���[�v�̗�O���X���[����B<BR>
     *       �E�ڋq�R�[�h != ���� <BR>
     *        �E�ڋq�R�[�h.length != 6<BR>
     * <BR>
     * (*1)���������̎���敪<BR>
     * �@@�@@�E"�������t����"<BR>
     * �@@�@@�E"�������t����"<BR>
     * <BR>
     * (*2)�M�p����̎���敪<BR>
     * �@@�@@�E"�V�K��������"<BR>
     * �@@�@@�E"�V�K��������"<BR>
     * �@@�@@�E"�����ԍϒ���"<BR>
     * �@@�@@�E"�����ԍϒ���"<BR>
     * �@@�@@�E"��������"<BR>
     * �@@�@@�E"���n����"<BR>
     *<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4469235C0167
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@���X�R�[�h�`�F�b�N  
        //�@@�P�|�P�j�@@this.���X�R�[�h == null�̏ꍇ�A  
        //�@@�@@�@@�@@�@@�u���X�R�[�h��null�v�̗�O���X���[����B  
        if (this.branchCode == null || this.branchCode.length == 0) 
        {
            log.debug("���X�R�[�h��null�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02174,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h��null�ł��B");
        }

        //�@@�P�|�Q�j�@@this.���X�R�[�h�̗v�f�����ȉ��̏������s���B
        //�@@�@@�P�|�Q�|�P�j�@@this.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A
        //�@@�@@�@@�@@�@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B
        //�@@�@@�@@�@@�@@�@@�@@�E���X�R�[�h != ����
        //�@@�@@�@@�@@�@@�@@�@@�E���X�R�[�h.length != 3
        for (int i = 0; i < this.branchCode.length; i++)
        {
            if (!WEB3StringTypeUtility.isDigit(this.branchCode[i])
                || this.branchCode[i].length() != 3)
            {
                log.debug("���X�R�[�h�̓��͂��s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�̓��͂��s���ł��B");
            }
        }

        // �Q�j�@@�敨�^�I�v�V�����敪�`�F�b�N
        //�@@this.�敨�^�I�v�V�����敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        //�@@�Q�|�P�j�@@this.�敨�^�I�v�V�����敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A
        //�@@�@@�u�敨�^�I�v�V�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��v�̗�O���X���[����B
        //�@@�@@�@@�E"�敨"
        //�@@�@@�@@�E"�I�v�V����"
        if (this.fuOpDiv != null)
        {
            if (!(WEB3FuturesOptionDivDef.FUTURES.equals(this.fuOpDiv)
                || WEB3FuturesOptionDivDef.OPTION.equals(this.fuOpDiv)))
            {
                log.debug("�敨�^�I�v�V�����敪�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01737,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�敨�^�I�v�V�����敪�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }

        // �R�j�@@�w����ʃ`�F�b�N
        //�@@this.�w����� != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        //�@@�R�|�P�j�@@this.�w����� != �����̏ꍇ�A
        //�@@�@@�u�w����ʂ������ȊO�v�̗�O���X���[����B
        //�@@�R�|�Q�j�@@this.�w����� != 4���̏ꍇ�A
        //�@@�@@�u�w����ʌ����G���[�v�̗�O���X���[����B
        if (this.targetProductCode != null)
        {
            if (!WEB3StringTypeUtility.isDigit(this.targetProductCode))
            {
                log.debug("�w����ʂ������ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02441,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w����ʂ������ȊO�̒l�ł��B");
            }
            
            if (this.targetProductCode.length() != 4)
            {
                log.debug("�w����ʂ̃T�C�Y���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02442,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w����ʂ̃T�C�Y���s���ł��B");
            }
        }

        // �S�j�@@�����`�F�b�N
        //�@@this.���� != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        //�@@�S�|�P�j�@@this.���� != �����̏ꍇ�A
        //�@@�@@�u�����������ȊO�v�̗�O���X���[����B
        //�@@�S�|�Q�j�@@this.���� != yyyyMM�`���̒l�ł������ꍇ�A
        //�@@�u�������t�`���G���[�v�̗�O���X���[����B
        if (this.delivaryMonth != null)
        {
            if (!WEB3StringTypeUtility.isDigit(this.delivaryMonth))
            {
                log.debug("�����������ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02351,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����������ȊO�̒l�ł��B");
            }

            if (!WEB3StringTypeUtility.isDateStr(this.delivaryMonth, "yyyyMM"))
            {
                log.debug("�������x�x�x�x�l�l�`���œ��͂��Ă��������B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00268,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������x�x�x�x�l�l�`���œ��͂��Ă��������B");
            }
        }

        // �T�j�@@�s�g���i�`�F�b�N
        //�@@this.�s�g���i != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        //�@@�T�|�P�j�@@this.�s�g���i != �����̏ꍇ�A
        //�@@�@@�u�s�g���i�������ȊO�v�̗�O���X���[����B
        //�@@�T�|�Q�j�@@this.�s�g���i <= 0 �̏ꍇ�A
        //�@@�@@�u�s�g���i��0�ȉ��v�̗�O���X���[����B
        //�@@�T�|�R�j�@@this.�s�g���i > 8���̒l�ł������ꍇ�A
        //�@@�@@�u�s�g���i�����G���[�v�̗�O���X���[����B
        if (this.strikePrice != null)
        {
            if (!WEB3StringTypeUtility.isNumber(this.strikePrice))
            {
                log.debug("�s�g���i�������ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00272,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�s�g���i�������ȊO�̒l�ł��B");
            }

            if (Double.parseDouble(this.strikePrice) <= 0)
            {
                log.debug("�s�g���i��0�ȉ��̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00273,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�s�g���i��0�ȉ��̒l�ł��B");
            }

            if (this.strikePrice.length() > 8)
            {
                log.debug("�s�g���i�̃T�C�Y���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00274,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�s�g���i�̃T�C�Y���s���ł��B");
            }
        }

        // �U�j�@@�I�v�V�������i�敪�`�F�b�N
        //�@@this.�I�v�V�������i�敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        //�@@�U�|�P�j�@@this.�I�v�V�������i�敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A
        //�@@�@@�u�I�v�V�������i�敪������`�̒l�v�̗�O���X���[����B
        //�@@�@@�@@�E"�v�b�g"
        //�@@�@@�@@�E"�R�[��"
        if (this.opProductType != null)
        {
            if (!(WEB3IfoProductTypeDef.PUT_OPTIONS.equals(this.opProductType)
                || WEB3IfoProductTypeDef.CALL_OPTIONS.equals(this.opProductType)))
            {
                log.debug("�I�v�V�������i�敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00270,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�I�v�V�������i�敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }

        //�V�j�@@�����w��`�F�b�N
        //�@@�V�|�P�j�@@�������������͂���Ă���ꍇ�A
        //�@@�@@(this.�敨�^�I�v�V�����敪 != null or
        //�@@�@@�@@this.�w����� != null or
        //�@@�@@�@@this.���� != null or
        //�@@�@@�@@this.�s�g���i != null or
        //�@@�@@�@@this.�I�v�V�������i�敪 != null)
        //�@@�@@�ȉ��̏����̂�����ɂ��Y�����Ȃ���΁A�u�����w��G���[�v�̗�O���X���[����B
        //�@@�@@�@@�������������i�敨�j
        //�@@�@@�@@this.�敨�^�I�v�V�����敪 == "�敨" ����
        //�@@�@@�@@this.�w����� != null ����
        //�@@�@@�@@this.���� != null ����
        //�@@�@@�@@this.�s�g���i == null ����
        //�@@�@@�@@this.�I�v�V�������i�敪 == null
        //�@@�@@�A�������������i�I�v�V�����j
        //�@@�@@�@@this.�敨�^�I�v�V�����敪 == "�I�v�V����" ����
        //�@@�@@�@@this.�w����� != null ����
        //�@@�@@�@@this.���� != null ����
        //�@@�@@�@@this.�s�g���i != null ����
        //�@@�@@�@@this.�I�v�V�������i�敪 != null
        if (this.fuOpDiv != null
            || this.targetProductCode != null
            || this.delivaryMonth != null
            || this.strikePrice != null
            || this.opProductType != null)
        {
            boolean l_blnIfoFlag = false;
            boolean l_blnOpFlag = false;
            if (WEB3FuturesOptionDivDef.FUTURES.equals(this.fuOpDiv)
                && this.targetProductCode != null
                && this.delivaryMonth != null
                && this.strikePrice == null
                && this.opProductType == null)
            {
                l_blnIfoFlag = true;
            }
            
            if (WEB3FuturesOptionDivDef.OPTION.equals(this.fuOpDiv)
                && this.targetProductCode != null
                && this.delivaryMonth != null
                && this.strikePrice != null
                && this.opProductType != null)
            {
                l_blnOpFlag = true;
            }
            
            if (!(l_blnIfoFlag || l_blnOpFlag))
            {
                log.debug("�����w��G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00334,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����w��G���[�B");
            }
        }

        //8�j�@@����敪�ꗗ�`�F�b�N
        //�@@8�|�P�j�@@this.����敪�ꗗ == null�ł���΁A
        //�@@�@@�u����敪�����w��ł��B�v�̗�O���X���[����B
        if (this.tradingTypeList == null || this.tradingTypeList.length == 0)
        {
            log.debug("����敪�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00601,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����敪�����w��ł��B");
        }

        //�@@8�|�Q�j�@@this.����敪�ꗗ�ɉ��L�̍��ڈȊO���ݒ�
        //�@@�@@����Ă�����A�u����敪�����݂��Ȃ��R�[�h�l�ł��B�v��
        //�@@�@@��O���X���[����B
        //    601�F�@@�敨�V�K��������
        //    602�F�@@�敨�V�K��������
        //    603�F�@@�敨�������ԍϒ���
        //    604�F�@@�敨�������ԍϒ���
        //    605�F�@@OP�V�K��������
        //    606�F�@@OP�V�K��������
        //    607�F�@@OP�������ԍϒ���
        //    608�F�@@OP�������ԍϒ���
        for (int i = 0; i < this.tradingTypeList.length; i++)
        {
            if (!(String.valueOf(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.intValue()).equals(this.tradingTypeList[i])))
            {
                log.debug("����敪�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00602,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����敪�����݂��Ȃ��R�[�h�l�ł��B");
            }

        }
         
        //9�j�@@�ڋq�R�[�h�`�F�b�N 
        //�@@this.�ڋq�R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B 
        //�@@9�|�P�j�@@this.�ڋq�R�[�h���ȉ��̏����ɊY������ꍇ�A  
        //�@@�@@�@@�@@�u�ڋq�R�[�h�G���[�v�̗�O���X���[����B  
        //�@@�@@�@@�@@�E�ڋq�R�[�h != ����  
        //�@@�@@�@@�@@�E�ڋq�R�[�h.length != 6  
        if (this.accountCode != null) 
        {
            if (!WEB3StringTypeUtility.isDigit(this.accountCode)
                || this.accountCode.length() != 6)
            {
                log.debug("�ڋq�R�[�h�̓��͂��s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq�R�[�h�̓��͂��s���ł��B");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
