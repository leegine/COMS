head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityLapseTargetOrderCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������Ώے�������(WEB3AdminEquityLapseTargetOrderCondition.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/30�@@�юu��(���u) �V�K�쐬
Revesion History : 2007/12/17  ��іQ(���u) �d�l�ύX���f��No.169
*/

package webbroker3.eqtypeadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (���������Ώے�������)<BR>
 * ���������Ώے�������<BR>
 * <BR>
 * @@author �юu��(���u)
 * @@version 1.0
 */

public class WEB3AdminEquityLapseTargetOrderCondition extends Message 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityLapseTargetOrderCondition.class);
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h�̔z�� <BR>
     * <BR>
     * �����X�R�[�h�����͎��́APR�w�ŕێ����Ă��� <BR>
     * �@@�戵�\���X�R�[�h�ꗗ���Z�b�g�����B<BR>
     */
    public String[] branchCode;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h
     */
    public String productCode = null;
    
    /**
     * (�s��R�[�h�ꗗ)<BR>
     * �s��R�[�h�̈ꗗ
     */
    public String[] marketList;
    
    /**
     * (����敪�ꗗ)<BR>
     * ����敪�̈ꗗ<BR>
     * <BR>
     * 1�F�@@�������t����<BR>
     * 2�F�@@�������t����<BR>
     * 3�F�@@�V�K��������<BR>
     * 4�F�@@�V�K��������<BR>
     * 5�F�@@�����ԍϒ���<BR>
     * 6�F�@@�����ԍϒ���<BR>
     * 7�F�@@��������<BR>
     * 8�F�@@���n����<BR>
     */
    public String[] tradingTypeList;
    
    /**
     * (�ٍϋ敪�ꗗ)<BR>
     * �ٍϋ敪�ꗗ<BR>
     * <BR>
     * 1�F���x�M�p<BR>
     * 2�F��ʐM�p<BR>
     */
    public String[] repaymentList = null;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     */
    public String accountCode = null;
    
    /**
     * (���������Ώے�������)<BR>
     * �R���X�g���N�^
     * @@return WEB3AdminEquityLapseTargetOrderCondition
     * @@roseuid 4469235C0148
     */
    public WEB3AdminEquityLapseTargetOrderCondition() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@���X�R�[�h�`�F�b�N <BR>
     * �@@�P�|�P�j�@@this.���X�R�[�h == null�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u���X�R�[�h��null�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02174<BR>
     * <BR>
     * �@@�P�|�Q�j�@@this.���X�R�[�h�̗v�f�����ȉ��̏������s���B<BR> 
     * �@@�@@�P�|�Q�|�P�j�@@this.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�@@�@@�@@�E���X�R�[�h != ����<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�E���X�R�[�h.length != 3 <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00779<BR>
     * <BR>
     * �Q�j�@@�����R�[�h�`�F�b�N <BR>
     * �@@this.�����R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * �@@�Q�|�P�j�@@this.�����R�[�h���ȉ��̏����ɊY������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�����R�[�h�G���[�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�E�����R�[�h != ���� <BR>
     * �@@�@@�@@�@@�E�����R�[�h.length != 5 <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01067<BR>
     * <BR>
     * �R�j�@@�s��R�[�h�ꗗ�`�F�b�N<BR>
     * �@@�R�|�P�j�@@this.�s��R�[�h�ꗗ == null�ł���΁A<BR>
     * �@@�@@�u�s��R�[�h�����w��ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00443<BR>
     * <BR>
     * �@@�R�|�Q�j�@@this.�s��R�[�h�ꗗ�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�E"����" <BR>
     * �@@�@@�@@�E"���" <BR>
     * �@@�@@�@@�E"���É�" <BR>
     * �@@�@@�@@�E"����" <BR>
     * �@@�@@�@@�E"�D�y" <BR>
     * �@@�@@�@@�E"NNM" <BR>
     * �@@�@@�@@�E"JASDAQ" <BR>
     * �@@�@@�@@�E"JNX-PTS" <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00608<BR>
     * <BR>
     * �S�j�@@����敪�ꗗ�`�F�b�N<BR>
     * �@@�S�|�P�j�@@this.����敪�ꗗ == null�ł���΁A<BR>
     * �@@�@@�u����敪�����w��ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00601<BR>
     * <BR>
     * �@@�S�|�Q�j�@@this.����敪�ꗗ�ɉ��L�̍��ڈȊO���ݒ�<BR>
     * �@@�@@����Ă�����A�u����敪�����݂��Ȃ��R�[�h�l�ł��B�v��<BR>
     * �@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�E"�������t����"<BR>
     * �@@�@@�@@�E"�������t����"<BR>
     * �@@�@@�@@�E"�V�K��������"<BR>
     * �@@�@@�@@�E"�V�K��������"<BR>
     * �@@�@@�@@�E"�����ԍϒ���"<BR>
     * �@@�@@�@@�E"�����ԍϒ���"<BR>
     * �@@�@@�@@�E"��������"<BR>
     * �@@�@@�@@�E"���n����"<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00602<BR>
     * <BR>
     * �T�j�@@�ٍϋ敪�ꗗ�`�F�b�N<BR>
     * �@@�T�|�P�j�@@this.����敪�ꗗ�Ɍ��������̎���敪(*1)�̂�<BR>
     * �@@�@@���݂���ꍇ�Athis.�ٍϋ敪�ꗗ != null�Ȃ�΁A<BR>
     * �@@�@@�u������������ٍ͕ϋ敪�̎w��s�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02445<BR>
     * <BR>
     * �@@�T�|�Q�j�@@this.����敪�ꗗ�ɐM�p����̎���敪(*2)��<BR>
     * �@@�@@��ł��܂܂��ꍇ�Athis.�ٍϋ敪�ꗗ == null�Ȃ�΁A<BR>
     * �@@�@@�u�M�p����ٍ͕ϋ敪�̎w��K�{�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02447<BR>
     * <BR>
     * �@@�T�|�R�j�@@this.�ٍϋ敪�ꗗ != null�̏ꍇ�@@����<BR>
     * �@@�@@this.�ٍϋ敪�ꗗ�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�u�ٍϋ敪�̒l�����݂��Ȃ��R�[�h�l�ł��B�v��<BR>
     * �@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�E"���x�M�p"<BR>
     * �@@�@@�@@�E"��ʐM�p"<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00629<BR>
     * �@@<BR>
     * �U�j�@@�ڋq�R�[�h�`�F�b�N<BR>
     * �@@this.�ڋq�R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�U�|�P�j�@@this.�ڋq�R�[�h���ȉ��̏����ɊY������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�ڋq�R�[�h�G���[�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�E�ڋq�R�[�h != ���� <BR>
     * �@@�@@�@@�@@�E�ڋq�R�[�h.length != 6 <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00780<BR>
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
        
        //�Q�j�@@�����R�[�h�`�F�b�N  
        //�@@this.�����R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B  
        //�@@�Q�|�P�j�@@this.�����R�[�h���ȉ��̏����ɊY������ꍇ�A  
        //�@@�@@�@@�@@�u�����R�[�h�G���[�v�̗�O���X���[����B  
        //�@@�@@�@@�@@�E�����R�[�h != ����  
        //�@@�@@�@@�@@�E�����R�[�h.length != 5  
        if (this.productCode != null) 
        {
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
        
        //�R�j�@@�s��R�[�h�ꗗ�`�F�b�N 
        //�@@�R�|�P�j�@@this.�s��R�[�h�ꗗ == null�ł���΁A 
        //�@@�@@�u�s��R�[�h�����w��ł��B�v�̗�O���X���[����B 
        if (this.marketList == null || this.marketList.length == 0) 
        {
            log.debug("�s��R�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�s��R�[�h�����w��ł��B");
        }
        
        //�@@�R�|�Q�j�@@this.�s��R�[�h�ꗗ�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A 
        //�@@�@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B  
        //�@@�@@�@@�E"����"  
        //�@@�@@�@@�E"���"  
        //�@@�@@�@@�E"���É�"  
        //�@@�@@�@@�E"����"  
        //�@@�@@�@@�E"�D�y"  
        //�@@�@@�@@�E"NNM"  
        //�@@�@@�@@�E"JASDAQ"  
        //�@@�@@�@@�E"JNX-PTS" 
        for (int i = 0; i < this.marketList.length; i++) 
        {
            if (!(WEB3MarketCodeDef.TOKYO.equals(this.marketList[i])
                || WEB3MarketCodeDef.OSAKA.equals(this.marketList[i])
                || WEB3MarketCodeDef.NAGOYA.equals(this.marketList[i])
                || WEB3MarketCodeDef.FUKUOKA.equals(this.marketList[i])
                || WEB3MarketCodeDef.SAPPORO.equals(this.marketList[i])
                || WEB3MarketCodeDef.NNM.equals(this.marketList[i])
                || WEB3MarketCodeDef.JASDAQ.equals(this.marketList[i])
                || WEB3MarketCodeDef.JNX_PTS.equals(this.marketList[i])))
            {
                log.debug("�s��R�[�h�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�s��R�[�h�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }
        
        //�S�j�@@����敪�ꗗ�`�F�b�N 
        //�@@�S�|�P�j�@@this.����敪�ꗗ == null�ł���΁A 
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
        
        //�@@�S�|�Q�j�@@this.����敪�ꗗ�ɉ��L�̍��ڈȊO���ݒ� 
        //�@@�@@����Ă�����A�u����敪�����݂��Ȃ��R�[�h�l�ł��B�v�� 
        //�@@�@@��O���X���[����B 
        //�@@�@@�@@�E"�������t����" 
        //�@@�@@�@@�E"�������t����" 
        //�@@�@@�@@�E"�V�K��������" 
        //�@@�@@�@@�E"�V�K��������" 
        //�@@�@@�@@�E"�����ԍϒ���" 
        //�@@�@@�@@�E"�����ԍϒ���" 
        //�@@�@@�@@�E"��������" 
        //�@@�@@�@@�E"���n����" 
        for (int i = 0; i < this.tradingTypeList.length; i++) 
        {
            if (!(String.valueOf(OrderTypeEnum.EQUITY_BUY.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.EQUITY_SELL.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.MARGIN_LONG.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.MARGIN_SHORT.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.CLOSE_MARGIN_LONG.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.CLOSE_MARGIN_SHORT.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.SWAP_MARGIN_LONG.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.SWAP_MARGIN_SHORT.intValue()).equals(this.tradingTypeList[i]))) 
            {
                log.debug("����敪�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00602,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����敪�����݂��Ȃ��R�[�h�l�ł��B");                
            }
        
        }
        //�T�j�@@�ٍϋ敪�ꗗ�`�F�b�N 
        //�@@�T�|�P�j�@@this.����敪�ꗗ�Ɍ��������̎���敪(*1)�̂� 
        //�@@�@@���݂���ꍇ�Athis.�ٍϋ敪�ꗗ != null�Ȃ�΁A 
        //�@@�@@�u������������ٍ͕ϋ敪�̎w��s�v�̗�O���X���[����B
        boolean l_blnFlag = false;
        for (int i = 0; i < this.tradingTypeList.length; i++)
        {
            if (!(String.valueOf(OrderTypeEnum.EQUITY_BUY.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.EQUITY_SELL.intValue()).equals(this.tradingTypeList[i])))                                       
            {
                l_blnFlag = true;
                break;
            }
        }
        if (!l_blnFlag)
        {
            if (this.repaymentList != null && this.repaymentList.length != 0)
            {
                log.debug("������������ٍ͕ϋ敪�̎w��s�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02445,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "������������ٍ͕ϋ敪�̎w��s�B"); 
            }
        }
            
        //�@@�T�|�Q�j�@@this.����敪�ꗗ�ɐM�p����̎���敪(*2)�� 
        //�@@�@@��ł��܂܂��ꍇ�Athis.�ٍϋ敪�ꗗ == null�Ȃ�΁A 
        //�@@�@@�u�M�p����ٍ͕ϋ敪�̎w��K�{�v�̗�O���X���[����B 
        else
        {
            if (this.repaymentList == null || this.repaymentList.length == 0) 
            {
                log.debug("�M�p����ٍ͕ϋ敪�̎w��K�{�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02447,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�M�p����ٍ͕ϋ敪�̎w��K�{�B"); 
            }
        }
        
        //�@@�T�|�R�j�@@this.�ٍϋ敪�ꗗ != null�̏ꍇ�@@���� 
        //�@@�@@this.�ٍϋ敪�ꗗ�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A 
        //�@@�@@�u�ٍϋ敪�̒l�����݂��Ȃ��R�[�h�l�ł��B�v�� 
        //�@@�@@��O���X���[����B 
        //�@@�@@�@@�E"���x�M�p" 
        //�@@�@@�@@�E"��ʐM�p" 
        if (this.repaymentList != null && this.repaymentList.length != 0) 
        {
            for (int i = 0; i < this.repaymentList.length; i++) 
            {
                if (!WEB3MarginTradingDivDef.MARKET_MARGIN.equals(this.repaymentList[i])
                    && !WEB3MarginTradingDivDef.INSTITUTION_MARGIN.equals(this.repaymentList[i])) 
                {
                    log.debug("�ٍϋ敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00629,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�ٍϋ敪�̒l�����݂��Ȃ��R�[�h�l�ł��B"); 
                }
            }
        }
         
        //�U�j�@@�ڋq�R�[�h�`�F�b�N 
        //�@@this.�ڋq�R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B 
        //�@@�U�|�P�j�@@this.�ڋq�R�[�h���ȉ��̏����ɊY������ꍇ�A  
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
