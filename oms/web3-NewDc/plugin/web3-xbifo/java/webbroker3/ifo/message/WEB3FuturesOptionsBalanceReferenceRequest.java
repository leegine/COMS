head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.17.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOptionsBalanceReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�I�v�V�����c���Ɖ�N�G�X�g�N���X(WEB3FuturesOptionsBalanceReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/29 ������ �V�K�쐬         
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.ifo.define.WEB3IfoSettlementStateDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�����w���敨�I�v�V�����c���Ɖ�N�G�X�g)<BR>
 * �����w���敨�I�v�V�����c���Ɖ�N�G�X�g�N���X<BR>
 * @@author ������
 * @@version 1.0  
 */
public class WEB3FuturesOptionsBalanceReferenceRequest extends WEB3GenRequest
{
    /** logger. */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
    WEB3FuturesOptionsBalanceReferenceRequest.class);
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 2004012291504L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futuresOptions_balanceReference";
    
    /**
     * �敨�^�I�v�V�����敪<BR>
     * �@@1�F�敨 2�F�I�v�V����<BR>
     */
    public String fuOpDiv;
    
    /**
     * (�����R�[�h)<BR>
     * (���������w�莞�Ɏg�p)<BR>
     * <BR>
     * null�F�w��Ȃ�<BR>
     * �敨OP�����R�[�h<BR>
     */
    public String productCode = null;
    
    /**
     * (���Ϗ�ԋ敪)<BR>
     * (���������w�莞�Ɏg�p)<BR>
     * <BR>
     * null�F�w��Ȃ� <BR>
     * 1�F������<BR>
     * 2�F���ϒ�<BR>
     */
    public String settlementState = null;

    /**
     * (����s��)<BR>
     * <BR>
     *(���������w�莞�Ɏg�p)<BR>
     * <BR>
     * null�F�w��Ȃ�<BR>
     * 1�F����<BR>
     * 2�F���<BR>
     * <BR>
     *���������荀�ڂɂ������w��̏ꍇ�A�ݒ肳���<BR>
     */
    public String marketCode;
    
    /**
     * (�w�����)<BR>
     * <BR>
     *(���������w�莞�Ɏg�p)<BR>
     * <BR>
     * null�F�w��Ȃ�<BR>
     * 0005�FTOPIX<BR>
     * 0018�F���o225<BR>
     * 0016�F���o300<BR>
     * 0019�F�~�j���o225<BR>
     * <BR>
     *���������荀�ڂɂ������w��̏ꍇ�A�ݒ肳���<BR>
     */
    public String targetProductCode;
    
    /**
     * (����)<BR>
     * <BR>
     *(���������w�莞�Ɏg�p)<BR>
     * <BR>
     * null�F�w��Ȃ�<BR>
     * ����(YYYYMM�`��)<BR>
     * <BR>
     *���������荀�ڂɂ������w��̏ꍇ�A�ݒ肳���<BR>
     */
    public String delivaryMonth;
    
    /**
     * (�I�v�V�������i�敪)<BR>
     * <BR>
     *(���������w�莞�Ɏg�p)<BR>
     * <BR>
     * null�F�w��Ȃ�<BR>
     * P�F�v�b�g�I�v�V����<BR>
     * C�F�R�[���I�v�V����<BR>
     * <BR>
     *���������荀�ڂɂ������w��̏ꍇ�A�ݒ肳���<BR>
     */
    public String opProductType;
    
    /**
     * (�s�g���i)<BR>
     * <BR>
     *(���������w�莞�Ɏg�p)<BR>
     * <BR>
     * null�F�w��Ȃ�<BR>
     * �s�g���i<BR>
     * <BR>
     *���������荀�ڂɂ������w��̏ꍇ�A�ݒ肳���<BR>
     */
    public String strikePrice;
    
    /**
     * (�����w���敨�I�v�V�����\�[�g�L�[)<BR>
     * �Ώۍ��ځF�����R�[�h�A�����A���敪�A���v�A���v�i���o��j<BR>
     */
    public WEB3FuturesOptionsSortKey[] sortKeys;
    
    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �\�����������y�[�W�ʒu���w��@@<BR>
     * ���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex;
    
    /**
     * (�y�[�W���\���s��)<BR>
     * �P�y�[�W���ɕ\�����������s�����w��<BR>
     */
    public String pageSize;
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�敨�^�I�v�V�����敪�̃`�F�b�N<BR>
     * �@@�P�|�P�jnull�̏ꍇ�A��O�Ƃ���B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01736<BR>
     * �@@�P�|�Q�j�ȉ��̍��ڈȊO�����݂����ꍇ�A��O�Ƃ���B<BR>
     * �@@�@@�@@�E1(�敨)<BR>
     * �@@�@@�@@�E2(�I�v�V����)<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01737<BR>
     * <BR>
     * �Q�j�@@���Ϗ�ԋ敪�̃`�F�b�N<BR>
     * �@@�@@�@@�ȉ��̏�ԈȊO�����݂����ꍇ�A��O�Ƃ���B<BR>
     * �@@�@@�@@�Enull(�w��Ȃ�) <BR>
     *       �E1(������)<BR>
     *       �E2(���ϒ�)<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00233<BR>
     * <BR>
     * �R�j�@@�����w���敨�I�v�V�����\�[�g�L�[�`�F�b�N <BR>
     * �@@�R�|�P�jthis.�����w���敨�I�v�V�����\�[�g�L�[�� <BR>
     * �@@�@@�@@�@@null�̒l�ł���Η�O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00231<BR>
     * �@@�R�|�Q�jthis.�����w���敨�I�v�V�����\�[�g�L�[�̗v�f����<BR> 
     * �@@�@@�@@�@@�O�ł���Η�O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00232<BR>
     * �@@�R�|�R�jthis.�����w���敨�I�v�V�����\�[�g�L�[�̗v�f����<BR> 
     * �@@�@@�@@�@@�J��Ԃ��ă`�F�b�N���s���B <BR>
     * �@@�@@�R�|�R�|�P�j�����w���敨�I�v�V�����\�[�g�L�[.�L�[���ڂ�null�̒l�ł���Η�O���X���[����B<BR> 
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00085<BR>
     * �@@�@@�R�|�R�|�Q�j�����w���敨�I�v�V�����\�[�g�L�[.�L�[���ڂɈȉ��̍��ږ��ȊO�̒l��<BR> 
     * �@@�@@�@@�@@�@@�@@�@@���݂������O���X���[����B<BR>
     * �@@�@@�@@�E�敨OP�c���Ɖ��.�����R�[�h<BR>
     * �@@�@@�@@�E�敨OP�c���Ɖ��.���敪<BR>
     * �@@�@@�@@�E�敨OP�c���Ɖ��.����<BR>
     * �@@�@@�@@�E�敨OP�c���Ɖ��.���v<BR>
     * �@@�@@�@@�E�敨OP�c���Ɖ��.���v(���o�)<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00086<BR>
     * �@@�@@�R�|�R�|�R�j�����w���敨�I�v�V�����\�[�g�L�[.�����^�~����null�̒l�ł���Η�O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00087<BR>
     * �@@�@@�R�|�R�|�S�j�����w���敨�I�v�V�����\�[�g�L�[.�����^�~�����ȉ��̒l�ȊO�ł���Η�O���X���[����B <BR>
     * �@@�@@�@@�E�hA�F�����h <BR>
     * �@@�@@�@@�E�hD�F�~���h<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00088<BR>
     * <BR>
     * �S�j�@@�v���y�[�W�ԍ��̃`�F�b�N<BR>
     * ���N�G�X�g�f�[�^�D�v���y�[�W�ԍ������w��̏ꍇ�A<BR>
     * ���N�G�X�g�f�[�^�D�v���y�[�W�ԍ��Ɂu�P�v���Z�b�g����B<BR>
     * <BR>
     * �T�j�@@�y�[�W���\���s���̃`�F�b�N<BR>
     * ���N�G�X�g�f�[�^�D�y�[�W���\���s�����O�A�܂��͖��w��̏ꍇ�A<BR>
     * ��O�Ƃ���B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00091<BR>
     * <BR>
     * �U�j�@@�����ݒ�`�F�b�N<BR>
     *   �U�|�P�j�ȉ��̑S�Ẵ��N�G�X�g���ڂ�ݒ肵�Ă���ꍇ�A��O���X���[����B<BR>
     *        �@@(�����R�[�h�Ɩ������荀�ڂ��ǂ�����ݒ肳��Ă���ꍇ)<BR>
     *      �敨�̏ꍇ(�敨/�I�v�V�����敪���敨)<BR>
     *          �E�����R�[�h<BR>
     *          �E����s��<BR>
     *          �E�w�����<BR>
     *      �I�v�V�����̏ꍇ(�敨/�I�v�V�����敪���I�v�V����)<BR>
     *          �E�����R�[�h<BR>
     *          �E����s��<BR>
     *          �E�w�����<BR>
     *          �E����<BR>
     *          �E�I�v�V�������i�敪<BR>
     *          �E�s�g���i<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00830<BR>
     * �@@�U�|�Q�j�������荀�ڂɂ������w��̏ꍇ<BR>
     *      �E�敨�̏ꍇ(�敨/�I�v�V�����敪���敨)<BR>
     *�@@�@@      ����s��A�w����ʁA�����̑S�Ă��ݒ肳��Ă��Ȃ���΁A��O���X���[����B<BR>
     *      �E�I�v�V�����̏ꍇ(�敨/�I�v�V�����敪���I�v�V����)<BR>
     *          ����s��A�w����ʁA�����A�I�v�V�������i�敪�A�s�g���i�̑S�Ă��ݒ肳��Ă��Ȃ���΁A��O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00830<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41AAB56A00E0
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

         //�P�j�@@�敨�^�I�v�V�����敪�̃`�F�b�N
        if (WEB3StringTypeUtility.isEmpty(this.fuOpDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01736,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�P�|�Q�j�ȉ��̍��ڈȊO�����݂����ꍇ�A��O�Ƃ���B
        if (!WEB3FuturesOptionDivDef.FUTURES.equals(this.fuOpDiv) && !WEB3FuturesOptionDivDef.OPTION.equals(this.fuOpDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01737,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�Q�j�@@���Ϗ�ԋ敪�̃`�F�b�N�ȉ��̏�ԈȊO�����݂����ꍇ�A��O�Ƃ���B
        //null(�w��Ȃ�) �E1(������)<BR>�E2(���ϒ�)
        if (this.settlementState != WEB3IfoSettlementStateDef.NOT_DESIGNATION
                && !WEB3IfoSettlementStateDef.HAVE_NOT_SETTLED.equals(this.settlementState)
                && !WEB3IfoSettlementStateDef.SETTLING.equals(this.settlementState))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00233,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�R�j�@@�����w���敨�I�v�V�����\�[�g�L�[�`�F�b�N
        //�R�|�P�jthis.�����w���敨�I�v�V�����\�[�g�L�[��null�̒l�ł���Η�O���X���[����B
        if (sortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�R�|�Q�jthis.�����w���敨�I�v�V�����\�[�g�L�[�̗v�f�����O�ł���Η�O���X���[����B
        if (sortKeys.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�R�|�R�j�\�[�g�L�[�̔z��̌����A�J��Ԃ��ă`�F�b�N���s���B
        for (int i = 0; i < sortKeys.length; i++)
        {
            //�R�|�R�|�P�j�����w���敨�I�v�V�����\�[�g�L�[.�L�[���ڂ�null�̒l�ł���Η�O���X���[����B
            if (WEB3StringTypeUtility.isEmpty(sortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                    getClass().getName() + "." + STR_METHOD_NAME);
            }
            //�R�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂɈȉ��̍��ږ��ȊO�̒l�����݂������O���X���[����B 
            if (!WEB3IfoKeyItemDef.BR_PRODUCTCODE.equals(sortKeys[i].keyItem) 
                && !WEB3IfoKeyItemDef.CONTRACT_DIVISION.equals(sortKeys[i].keyItem)
                && !WEB3IfoKeyItemDef.OPEN_DATE.equals(sortKeys[i].keyItem)
                && !WEB3IfoKeyItemDef.INCOME.equals(sortKeys[i].keyItem)
                && !WEB3IfoKeyItemDef.INCOME_COST.equals(sortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    getClass().getName() + "." + STR_METHOD_NAME);
            }
            //�R�|�R�|�R�j�\�[�g�L�[.�����^�~����null�̒l�ł���Η�O���X���[����B
            if (WEB3StringTypeUtility.isEmpty(sortKeys[i].ascDesc))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                    getClass().getName() + "." + STR_METHOD_NAME);
            }
            //�R�|�R�|�S�j�\�[�g�L�[.�����^�~�����ȉ��̒l�ȊO�ł���Η�O���X���[����B
            // A:�����AD�F�~��
            if (!WEB3AscDescDef.ASC.equals(sortKeys[i].ascDesc)
                && !WEB3AscDescDef.DESC.equals(sortKeys[i].ascDesc))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        
        //�S�j�@@�v���y�[�W�ԍ��̃`�F�b�N
        //���N�G�X�g�f�[�^�D�v���y�[�W�ԍ������w��̏ꍇ�A
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            // ���N�G�X�g�f�[�^�D�v���y�[�W�ԍ��Ɂu�P�v���Z�b�g����B
            this.pageIndex = "1";
        }
        
        // �T�j�@@�v���y�[�W�ԍ��̃`�F�b�N
        //���N�G�X�g�f�[�^�D�y�[�W���\���s�����O�A�܂��͖��w��̏ꍇ�A
        if (WEB3StringTypeUtility.isEmpty(this.pageSize) || "0".equals(this.pageSize))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�敨/�I�v�V�����敪�ɂ�肻�ꂼ��̖����ݒ�`�F�b�N���s���B        
        if (WEB3FuturesOptionDivDef.FUTURES.equals(fuOpDiv))
        {
            //�U�|�P�j�����R�[�h�Ɩ������荀�ڂ��ǂ�����ݒ肳��Ă���ꍇ�͗�O���X���[����B
            if((productCode != null)
                && (marketCode != null)
                && (targetProductCode != null)
                && (delivaryMonth != null))
            {
                throw new WEB3BusinessLayerException(         
                    WEB3ErrorCatalog.BUSINESS_ERROR_00830,         
                    this.getClass().getName() + "." + STR_METHOD_NAME,        
                    "�����R�[�h�Ɩ������荀�ڂ��ǂ�����ݒ肳��Ă��܂��B");
            }
            // �@@�U�|�Q�j�������荀�ڂɂ������w��̏ꍇ
            //�@@�@@      ����s��A�w����ʁA�����̑S�Ă��ݒ肳��Ă��Ȃ���΁A��O���X���[����B
            if((marketCode==null)
                &&(targetProductCode==null)
                &&(delivaryMonth==null))          
            {              
                return;            
            }
            else              
            {              
                if((marketCode==null)
                    ||(targetProductCode==null)
                    ||(delivaryMonth==null))      
                {          
                    throw new WEB3BusinessLayerException(  
                        WEB3ErrorCatalog.BUSINESS_ERROR_00830, 
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�������荀�ڂ̂����ꂩ���ݒ肳��Ă��܂���B");
                }          
            } 
        }
        else if (WEB3FuturesOptionDivDef.OPTION.equals(fuOpDiv))
        {
            //�U�|�P�j�����R�[�h�Ɩ������荀�ڂ��ǂ�����ݒ肳��Ă���ꍇ�͗�O���X���[����B
            if((productCode != null)
                && (marketCode != null)
                && (targetProductCode != null)
                && (delivaryMonth != null)
                && (opProductType != null)
                && (strikePrice != null))
            {
                throw new WEB3BusinessLayerException(         
                    WEB3ErrorCatalog.BUSINESS_ERROR_00830,         
                    this.getClass().getName() + "." + STR_METHOD_NAME,        
                    "�����R�[�h�Ɩ������荀�ڂ��ǂ�����ݒ肳��Ă��܂��B");
            }
            // �@@�U�|�Q�j�������荀�ڂɂ������w��̏ꍇ
            //�@@�@@      ����s��A�w����ʁA�����̑S�Ă��ݒ肳��Ă��Ȃ���΁A��O���X���[����B
            if((marketCode==null)
                &&(targetProductCode==null)
                &&(delivaryMonth==null)                
                &&(opProductType==null)
                &&(strikePrice==null))          
            {              
                return;            
            }
            else              
            {              
                if((marketCode==null)
                    ||(targetProductCode==null)
                    ||(delivaryMonth==null)            
                    ||(opProductType==null)
                    ||(strikePrice==null))      
                {          
                    throw new WEB3BusinessLayerException(  
                        WEB3ErrorCatalog.BUSINESS_ERROR_00830, 
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�������荀�ڂ̂����ꂩ���ݒ肳��Ă��܂���B");
                }
            }
        }
        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FuturesOptionsBalanceReferenceResponse(this);
    }
}
@
