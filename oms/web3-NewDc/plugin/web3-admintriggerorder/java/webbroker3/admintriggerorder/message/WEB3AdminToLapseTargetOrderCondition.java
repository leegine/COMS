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
filename	WEB3AdminToLapseTargetOrderCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����Ώے�������(WEB3AdminToLapseTargetOrderCondition.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/20�@@�]�V�q(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����Ώے�������)<BR>
 * �����Ώے�������<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3AdminToLapseTargetOrderCondition extends Message 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToLapseTargetOrderCondition.class);
    
    /**
     * (����ID)<BR>
     * ����ID<BR>
     */
    public String id = null;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h�̔z�� <BR>
     * <BR>
     * �����X�R�[�h�����͎��́APR�w�ŕێ����Ă��� <BR>
     * �@@�戵�\���X�R�[�h�ꗗ���Z�b�g�����B<BR>
     */
    public String[] branchCode;
    
    /**
     * (����������ʈꗗ)<BR>
     * �����������<BR>
     * <BR>
     * 1�F�@@�A������<BR>
     * 2�F�@@OCO����<BR>
     * 3�F�@@IFD����<BR>
     * 4�F�@@�t�w�l����<BR>
     * 5�F�@@W�w�l����<BR>
     * <BR>
     * ��"�A������"�͒�`�㑶�݂��邪�A�{�N���X�ł͎g�p����Ȃ��B<BR>
     */
    public String[] triggerOrderTypeList = null;
    
    /**
     * (���i�敪�ꗗ)<BR>
     * ���i�敪 <BR>
     * <BR>
     * 1�F�@@�������� <BR>
     * 2�F�@@�M�p��� <BR>
     * 3�F�@@�敨<BR>
     * 4�F�@@�I�v�V����<BR>
     */
    public String[] productDivList;
    
    /**
     * (�s��R�[�h�ꗗ)<BR>
     * �s��R�[�h<BR>
     */
    public String[] marketList = null;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode = null;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode = null;
    
    /**
     * (�����Ώے�������)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 44051F270167
     */
    public WEB3AdminToLapseTargetOrderCondition() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@����ID�`�F�b�N<BR>
     * �@@this.����ID != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�P�|�P�j�@@this.����ID != �����̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u����ID�������ȊO�v�̗�O���X���[����B <BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_01476<BR>
     * <BR>
     * �Q�j�@@���X�R�[�h�`�F�b�N <BR>
     * �@@�Q�|�P�j�@@this.���X�R�[�h == null�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u���X�R�[�h��null�v�̗�O���X���[����B <BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_02174<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@this.���X�R�[�h�̗v�f�����ȉ��̏������s���B <BR>
     * �@@�@@�Q�|�Q�|�P�j�@@this.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�@@�@@�@@�E���X�R�[�h != ���� <BR>
     * �@@�@@�@@�@@�@@�@@�@@�E���X�R�[�h.length != 3 <BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_00779<BR>
     * <BR>
     * �R�j�@@����������ʈꗗ�`�F�b�N<BR>
     * �@@�R�|�P�j�@@ID���ڎw�莞�łȂ��i����ID == null�j�ꍇ�A<BR>
     * �@@�@@this.����������ʈꗗ == null�ł���΁A<BR>
     * �@@�@@�u����������ʂ����w��ł��B�v�̗�O���X���[����B<BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_02396<BR>
     * <BR>
     * �@@�R�|�Q�j�@@this.����������ʈꗗ != null�̏ꍇ�A<BR>
     * �@@�@@this.����������ʈꗗ�ɉ��L�̍��ڈȊO��<BR>
     * �@@�@@�ݒ肳��Ă����ꍇ�A�u����������ʂ�����`�̒l�ł��B�v��<BR>
     * �@@�@@��O���X���[����B <BR>
     * �@@�@@�@@�E"�A������" <BR>
     * �@@�@@�@@�E"OCO����"<BR>
     * �@@�@@�@@�E"IFD����"<BR>
     * �@@�@@�@@�E"�t�w�l����" <BR>
     * �@@�@@�@@�E"W�w�l����"<BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_02397<BR>
     * <BR>
     * �S�j�@@���i�敪�ꗗ�`�F�b�N<BR>
     * �@@�S�|�P�j�@@this.���i�敪�ꗗ == null�̏ꍇ�A<BR>
     * �@@�@@�u���i�敪�ꗗ�����w��ł��B�v�̗�O���X���[����B<BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_01462<BR>
     * <BR>
     * �@@�S�|�Q�j�@@this.���i�敪�ꗗ�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A <BR>
     * �@@�@@�@@�@@�u���i�敪�����݂��Ȃ��R�[�h�l�ł��B�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�E"��������" <BR>
     * �@@�@@�@@�@@�E"�M�p���" <BR>
     * �@@�@@�@@�@@�E"�敨"<BR>
     * �@@�@@�@@�@@�E"�I�v�V����" <BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_01068<BR>
     * <BR>
     * �T�j�@@�s��R�[�h�ꗗ�`�F�b�N<BR>
     * �@@�T�|�P�j�@@this.���i�敪�ꗗ��"��������" or "�M�p���"���܂܂��ꍇ�A<BR>
     * �@@�@@ID���ڎw�莞�łȂ��i����ID == null�j�@@����<BR>
     * �@@�@@this.�s��R�[�h�ꗗ == null�ł���΁A<BR>
     * �@@�@@�u�s��R�[�h�����w��ł��B�v�̗�O���X���[����B<BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_00443<BR>
     * <BR>
     * �@@�T�|�Q�j�@@this.�s��R�[�h�ꗗ != null�̏ꍇ�A<BR>
     * �@@�@@this.�s��R�[�h�ꗗ�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
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
     * �U�j�@@�����R�[�h�`�F�b�N <BR>
     * �@@this.�����R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * �@@�U�|�P�j�@@this.�����R�[�h���ȉ��̏����ɊY������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�����R�[�h�G���[�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�E�����R�[�h != ���� <BR>
     * �@@�@@�@@�@@�E�����R�[�h.length != 5 <BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_01067<BR>
     * <BR>
     * �V�j�@@�ڋq�R�[�h�`�F�b�N<BR>
     * �@@this.�ڋq�R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�V�|�P�j�@@this.�ڋq�R�[�h���ȉ��̏����ɊY������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�ڋq�R�[�h�G���[�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�E�ڋq�R�[�h != ���� <BR>
     * �@@�@@�@@�@@�E�ڋq�R�[�h.length != 6 <BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_00780<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44051F23035B
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@����ID�`�F�b�N
        //�@@this.����ID != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (WEB3StringTypeUtility.isNotEmpty(this.id))
        {
            //�P�|�P�j�@@this.����ID != �����̏ꍇ�A�u����ID�������ȊO�v�̗�O���X���[����B
            if (!WEB3StringTypeUtility.isDigit(this.id))
            {
                log.debug("����ID�������ȊO�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01476,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����ID�������ȊO�ł��B");
            }
        }
        
        //�Q�j�@@���X�R�[�h�`�F�b�N 
        //�@@�Q�|�P�j�@@this.���X�R�[�h == null�̏ꍇ�A�u���X�R�[�h��null�v�̗�O���X���[����B
        if (this.branchCode == null || this.branchCode.length == 0)
        {
            log.debug("���X�R�[�h��null�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02174,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h��null�ł��B");
        }
        
        //�Q�|�Q�j�@@this.���X�R�[�h�̗v�f�����ȉ��̏������s���B 
        //�@@�Q�|�Q�|�P�j�@@this.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A
        //�@@�@@�@@�@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B
        //�@@�@@�@@�@@�@@�@@�E���X�R�[�h != ����
        //�@@�@@�@@�@@�@@�@@�E���X�R�[�h.length != 3 
        int l_intlen = this.branchCode.length;
        for (int i = 0; i < l_intlen; i++)
        {
            if (!(WEB3StringTypeUtility.isDigit(this.branchCode[i]) 
                && this.branchCode[i].length() == 3))
            {
                log.debug("���X�R�[�h�̓��͂��s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�̓��͂��s���ł��B");
            }
        }
        
        //�R�j�@@����������ʈꗗ�`�F�b�N
        //�@@�R�|�P�j�@@ID���ڎw�莞�łȂ��i����ID == null�j�ꍇ�A
        //�@@�@@this.����������ʈꗗ == null�ł���΁A
        //�@@�@@�u����������ʂ����w��ł��B�v�̗�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.id) 
            && (this.triggerOrderTypeList == null || this.triggerOrderTypeList.length == 0))
        {
            log.debug("����������ʂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02396,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ����w��ł��B");
        }
        
        //�@@�R�|�Q�j�@@this.����������ʈꗗ != null�̏ꍇ�A
        //�@@�@@this.����������ʈꗗ�ɉ��L�̍��ڈȊO���ݒ肳��Ă����ꍇ�A
        //�@@�@@�u����������ʂ�����`�̒l�ł��B�v�̗�O���X���[����B
        //�@@�@@�@@�E"�A������" 
        //�@@�@@�@@�E"OCO����"
        //�@@�@@�@@�E"IFD����"
        //�@@�@@�@@�E"�t�w�l����"
        //�@@�@@�@@�E"W�w�l����"
        
        if (this.triggerOrderTypeList != null && this.triggerOrderTypeList.length != 0)
        {
            l_intlen = this.triggerOrderTypeList.length;
            for (int i = 0; i < l_intlen; i++)
            {
                if (!(WEB3TriggerOrderTypeDef.SUCC.equals(this.triggerOrderTypeList[i])
                    || WEB3TriggerOrderTypeDef.OCO.equals(this.triggerOrderTypeList[i])
                    || WEB3TriggerOrderTypeDef.IFD.equals(this.triggerOrderTypeList[i])
                    || WEB3TriggerOrderTypeDef.STOP.equals(this.triggerOrderTypeList[i])
                    || WEB3TriggerOrderTypeDef.W_LlIMIT.equals(this.triggerOrderTypeList[i])))
                {
                    log.debug("����������ʂ�����`�̒l�ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02397,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "����������ʂ�����`�̒l�ł��B");
                }
            }
        }
        
        //�S�j�@@���i�敪�ꗗ�`�F�b�N
        //�@@�S�|�P�j�@@this.���i�敪�ꗗ == null�̏ꍇ�A�u���i�敪�ꗗ�����w��ł��B�v��
        //�@@�@@�@@��O���X���[����B
        if (this.productDivList == null || this.productDivList.length == 0)
        {
            log.debug("���i�敪�ꗗ�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01462,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���i�敪�ꗗ�����w��ł��B");
        }
        
        //�@@�S�|�Q�j�@@this.���i�敪�ꗗ�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A
        //�@@�@@�@@�@@�u���i�敪�����݂��Ȃ��R�[�h�l�ł��B�v�̗�O���X���[����B
        //�@@�@@�@@�@@�E"��������" 
        //�@@�@@�@@�@@�E"�M�p���" 
        //�@@�@@�@@�@@�E"�敨"
        //�@@�@@�@@�@@�E"�I�v�V����" 
        l_intlen = this.productDivList.length;
        for (int i = 0; i < l_intlen; i++)
        {
            if (!(WEB3CommodityDivDef.EQUITY.equals(this.productDivList[i])
                || WEB3CommodityDivDef.MARGIN.equals(this.productDivList[i])
                || WEB3CommodityDivDef.FUTURE.equals(this.productDivList[i])
                || WEB3CommodityDivDef.OPTION.equals(this.productDivList[i])))
            {
                log.debug("���i�敪�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01068,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���i�敪�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }
        
        //�T�j�@@�s��R�[�h�ꗗ�`�F�b�N
        //�@@�T�|�P�j�@@this.���i�敪�ꗗ��"��������" or "�M�p���"���܂܂��ꍇ�A
        //�@@�@@ID���ڎw�莞�łȂ��i����ID == null�j�@@���@@
        //�@@�@@this.�s��R�[�h�ꗗ == null�ł���΁A
        //�@@�@@�u�s��R�[�h�����w��ł��B�v�̗�O���X���[����B
        boolean l_blnFlag = false;
        for (int i = 0; i < l_intlen; i++)
        {
            if (WEB3CommodityDivDef.EQUITY.equals(this.productDivList[i])
                || WEB3CommodityDivDef.MARGIN.equals(this.productDivList[i]))
            {
                l_blnFlag = true;
            }
        }
        
        if (l_blnFlag)
        {
            if (WEB3StringTypeUtility.isEmpty(this.id) 
                && (this.marketList == null || this.marketList.length == 0))
            {
                log.debug("�s��R�[�h�����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�s��R�[�h�����w��ł��B");
            }
        }
        
        //�@@�T�|�Q�j�@@this.�s��R�[�h�ꗗ != null�̏ꍇ�A
        //�@@�@@this.�s��R�[�h�ꗗ�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A
        //�@@�@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B
        //�@@�@@�@@�E"����"
        //�@@�@@�@@�E"���" 
        //�@@�@@�@@�E"���É�"
        //�@@�@@�@@�E"����"
        //�@@�@@�@@�E"�D�y"
        //�@@�@@�@@�E"NNM"
        //�@@�@@�@@�E"JASDAQ"
        if (this.marketList != null && this.marketList.length != 0)
        {
            l_intlen = this.marketList.length;
            for (int i = 0; i < l_intlen; i++)
            {
                if (!(WEB3MarketCodeDef.TOKYO.equals(this.marketList[i])
                    || WEB3MarketCodeDef.OSAKA.equals(this.marketList[i])
                    || WEB3MarketCodeDef.NAGOYA.equals(this.marketList[i])
                    || WEB3MarketCodeDef.FUKUOKA.equals(this.marketList[i])
                    || WEB3MarketCodeDef.SAPPORO.equals(this.marketList[i])
                    || WEB3MarketCodeDef.NNM.equals(this.marketList[i])
                    || WEB3MarketCodeDef.JASDAQ.equals(this.marketList[i])))
                {
                    log.debug("�s��R�[�h�����݂��Ȃ��R�[�h�l�ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�s��R�[�h�����݂��Ȃ��R�[�h�l�ł��B");
                }
            }
        }
        
        //�U�j�@@�����R�[�h�`�F�b�N
        //�@@this.�����R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (WEB3StringTypeUtility.isNotEmpty(this.productCode))
        {
            //�U�|�P�j�@@this.�����R�[�h���ȉ��̏����ɊY������ꍇ�A
            //�@@�@@�@@�u�����R�[�h�G���[�v�̗�O���X���[����B
            //�@@�@@�@@�E�����R�[�h != ����
            //�@@�@@�@@�E�����R�[�h.length != 5
            if (!(WEB3StringTypeUtility.isDigit(this.productCode)
                && this.productCode.length() == 5))
            {
                log.debug("�����R�[�h�̓��͂��s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����R�[�h�̓��͂��s���ł��B");
            }
        }
        
        //�V�j�@@�ڋq�R�[�h�`�F�b�N
        //�@@this.�ڋq�R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (WEB3StringTypeUtility.isNotEmpty(this.accountCode))
        {
            //�V�|�P�j�@@this.�ڋq�R�[�h���ȉ��̏����ɊY������ꍇ�A
            //�@@�@@�@@�u�ڋq�R�[�h�G���[�v�̗�O���X���[����B
            //�@@�@@�@@�E�ڋq�R�[�h != ����
            //�@@�@@�@@�E�ڋq�R�[�h.length != 6
            if (!(WEB3StringTypeUtility.isDigit(this.accountCode)
                && this.accountCode.length() == 6))
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
