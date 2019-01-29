head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.02.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3BVSBookValueSpecsRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �뉿�P�����׏Ɖ�N�G�X�g(WEB3BVSBookValueSpecsRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/05  �Ɍ��t(���u) �V�K�쐬
*/
package webbroker3.tradehistory.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.tradehistory.define.WEB3PlsBvsDisplayTermDef;
import webbroker3.tradehistory.define.WEB3PlsBvsProductCodeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�뉿�P�����׏Ɖ�N�G�X�g)<BR>
 * �뉿�P�����׏Ɖ�N�G�X�g�N���X<BR>
 * 
 * @@author �Ɍ��t
 * @@version 1.0 
 */
public class WEB3BVSBookValueSpecsRequest extends WEB3GenRequest 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3BVSBookValueSpecsRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "PLSBVS_bookValueSpecs";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411051040L;
    
    /**
     * (�\������)<BR>
     * �\������<BR>
     * <BR>
     * 0�F�@@�O�������ȍ~(DEFAULT)<BR>
     * 1�F�@@1������<BR>
     * 2�F�@@1�T�ԕ�<BR>
     * 3�F�@@�O��1����<BR>
     */
    public String displayTerm;
    
    /**
     * (���i�R�[�h)<BR>
     * ���i�R�[�h<BR>
     * <BR>
     * 10:�@@����<BR>
     * 11:�@@�M�p<BR>
     * 15:�@@�~�j��<BR>
     * 20:�@@���M<BR>
     * 21:�@@�O��<BR>
     * 22:�@@�ݓ�<BR>
     * 23:�@@MRF<BR>
     * 30:�@@��<BR>
     * 40:�@@�O��<BR>
     * 50:�@@����<BR>
     * 51�F ���w��OP<BR>
     * 52:�@@��<BR>
     * 53:�@@��OP<BR>
     * 54:�@@�XOP<BR>
     * 55:�@@�O��<BR>
     * 56:�@@�O��OP<BR>
     * 57:�@@��OP<BR>
     * 60:�@@�O��<BR>
     * 70:�@@��<BR>
     * 71:�@@��GP<BR>
     * 80:�@@����<BR>
     * 91:�@@CD<BR>
     * 92:�@@CP<BR>
     * 93:�@@BA<BR>
     * 99:�@@���K<BR>
     * <BR>
     */
    public String commodityCode;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String productName;
    
    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ�<BR>
     */
    public String pageIndex;
    
    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s��<BR>
     */
    public String pageSize;
    
    /**
     * @@roseuid 418877BB02BF
     */
    public WEB3BVSBookValueSpecsRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�\�����ԃ`�F�b�N<BR>
     * �@@�P�|�P�jthis.�\������ == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�\�����Ԃ�null�B<BR>
     *           class         :  WEB3BusinessLayerException           <BR>
     *           tag           :  BUSINESS_ERROR_01082              <BR>
     * <BR>
     * �@@�P�|�Q�jthis.�\�����Ԃ��ȉ��Ɏ����l�̂��Â�ɂ��Y�����Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�\�����Ԃ�����`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�E"0�F�O�������ȍ~(DEFAULT)"<BR>
     * �@@�@@�@@�@@�@@�@@�E"1�F1������"<BR>
     * �@@�@@�@@�@@�@@�@@�E"2�F1�T�ԕ�"<BR>
     * �@@�@@�@@�@@�@@�@@�E"3�F�O��1����"<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag           :  BUSINESS_ERROR_01083              <BR>
     * <BR>
     * �Q�j���i�R�[�h�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.���i�R�[�h == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���i�R�[�h��null�v�̗�O���X���[����B<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_01084              <BR>
     * <BR>
     * �@@�Q�|�Q�jthis.���i�R�[�h���ȉ��Ɏ����l�̂��Â�ɂ��Y�����Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���i�R�[�h������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�E"10:����"<BR>
     * �@@�@@�@@�@@�@@�@@�E"11:�M�p"<BR>
     *            �E"15:�~�j��"<BR>
     * �@@�@@�@@�@@�@@�@@�E"20:���M"<BR>
     * �@@�@@�@@�@@�@@�@@�E"21:�O��"<BR>
     * �@@�@@�@@�@@�@@�@@�E"22:�ݓ�"<BR>
     * �@@�@@�@@�@@�@@�@@�E"23:MRF"<BR>
     * �@@�@@�@@�@@�@@�@@�E"30:��"<BR>
     * �@@�@@�@@�@@�@@�@@�E"40:�O��"<BR>
     * �@@�@@�@@�@@�@@�@@�E"50:����"<BR>
     * �@@�@@�@@�@@�@@�@@�E"51:���w��OP"<BR>
     * �@@�@@�@@�@@�@@�@@�E"52:��"<BR>
     * �@@�@@�@@�@@�@@�@@�E"53:��OP"<BR>
     * �@@�@@�@@�@@�@@�@@�E"54:�XOP"<BR>
     * �@@�@@�@@�@@�@@�@@�E"55:�O��"<BR>
     * �@@�@@�@@�@@�@@�@@�E"56:�O��OP"<BR>
     * �@@�@@�@@�@@�@@�@@�E"57:��OP"<BR>
     * �@@�@@�@@�@@�@@�@@�E"60:�O��"<BR>
     * �@@�@@�@@�@@�@@�@@�E"70:��"<BR>
     * �@@�@@�@@�@@�@@�@@�E"71:��GP"<BR>
     * �@@�@@�@@�@@�@@�@@�E"80:����"<BR>
     * �@@�@@�@@�@@�@@�@@�E"91:CD"<BR>
     * �@@�@@�@@�@@�@@�@@�E"92:CP"<BR>
     * �@@�@@�@@�@@�@@�@@�E"93:BA"<BR>
     * �@@�@@�@@�@@�@@�@@�E"99:���K"<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag           :  BUSINESS_ERROR_01085              <BR>
     * <BR>
     * �R�j�@@�����R�[�h�`�F�b�N<BR>
     * �@@�R�|�P�jthis.�����R�[�h == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�����R�[�h��null�v�̗�O���X���[����B<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_00079              <BR>
     * <BR>
     * �@@�R�|�Q�jthis.�����R�[�h���ȉ��̏����ɊY������ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u�����R�[�h�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�Ethis.�����R�[�h != ���l<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_00815              <BR>
     * <BR>
     * �S�j�������`�F�b�N<BR>
     * �@@�S�|�P�jthis.������ == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u��������null�v�̗�O���X���[����B<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_01062              <BR>
     * <BR>
     * �T�j�v���y�[�W�ԍ��`�F�b�N<BR>
     * �@@�T�|�P�jthis.�v���y�[�W�ԍ� == null�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_00089              <BR>
     * <BR>
     * �@@�T�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_00090              <BR>
     * <BR>
     * �@@�T�|�R�jthis.�v���y�[�W�ԍ� <= 0�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_00616              <BR>
     * <BR>
     * �U�j�y�[�W���\���s���`�F�b�N<BR>
     * �@@�U�|�P�jthis.�y�[�W���\���s�� == null�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_00091              <BR>
     * <BR>
     * �@@�U�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_00092              <BR>
     * <BR>
     * �@@�U�|�R�jthis.�y�[�W���\���s�� <= 0�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_00617              <BR>
     * <BR>
     * @@roseuid 416CDEDB0381
     */
    public void validate() throws  WEB3BaseException
    {
        final  String  STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�\�����ԃ`�F�b�N
        //�\�����Ԃ�null�B
        if (this.displayTerm == null || "".equals(this.displayTerm))
        {
            log.error("�u�\�����Ԃ�null�v�̗�O���X���[����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01082,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //�\�����ԃ`�F�b�N
        //�u�\�����Ԃ�����`�̒l�v�̗�O���X���[����B
        if (!WEB3PlsBvsDisplayTermDef.DEFAULT.equals(this.displayTerm) &&
            !WEB3PlsBvsDisplayTermDef.ONE_MONTH.equals(this.displayTerm) &&
            !WEB3PlsBvsDisplayTermDef.ONE_WEEK.equals(this.displayTerm) &&
            !WEB3PlsBvsDisplayTermDef.THE_PREVIOUS_DAY.equals(this.displayTerm))
        {
            log.error("�u�\�����Ԃ�����`�̒l�v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01083,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //���i�R�[�h�`�F�b�N
        //�u���i�R�[�h��null�v�̗�O���X���[����B
        if (this.commodityCode == null || "".equals(this.commodityCode))
        {
            log.error("�u���i�R�[�h��null�v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01084,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //���i�R�[�h�`�F�b�N
        //�u���i�R�[�h������`�̒l�v�̗�O���X���[����B
        if (!WEB3PlsBvsProductCodeDef.EQUITY.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.MARGIN.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.MINISTOCK.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.MUTUAL.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.FOREIGN_MUTUAL.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.RUITO.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.MRF.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.BOND.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.FOREIGN_STOCK.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.STOCK_FUTURES.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.STOCK_INDEX_OP.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.BOND_FUTURES.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.BOND_FUTURES_OP.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.BRANCH_OP.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.FOREIGN_FUTURES.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.FOREIGN_FUTURES_OP.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.STOCK_OP.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.FOREIGN_BOND.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.CASH.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.CASH_GP.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.SPECIAL.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.CD.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.CP.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.BA.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.MONEY.equals(this.commodityCode))
        {
            log.error("�u���i�R�[�h������`�̒l�v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01085,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //�����R�[�h�`�F�b�N
        //�u�����R�[�h��null�v�̗�O���X���[����B
        if (this.productCode == null || "".equals(this.productCode))
        {
            log.error("�u�����R�[�h��null�v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + STR_METHOD_NAME);
        }
           
        //�������`�F�b�N
        //�u��������null�v�̗�O���X���[����B
        if (this.productName == null || "".equals(this.productName))
        {
            log.error("�u��������null�v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01062,
                this.getClass().getName() + STR_METHOD_NAME);
        }           
        
        //�v���y�[�W�ԍ��`�F�b�N
        //�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B
        if (this.pageIndex == null || "".equals(this.pageIndex))
        {
            log.error("�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //�v���y�[�W�ԍ��`�F�b�N
        //�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isNumber(this.pageIndex))
        {
            log.error("�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + STR_METHOD_NAME);
        }                 

        //�v���y�[�W�ԍ��`�F�b�N
        //�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B
        if (Long.parseLong(this.pageIndex) <= 0)
        {
            log.error("�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME);
        }   
        
        //�y�[�W���\���s���`�F�b�N
        //�u�y�[�W���\���s����null�v�̗�O���X���[����B
        if (this.pageSize == null || "".equals(this.pageSize))
        {
            log.error("�u�y�[�W���\���s����null�v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + STR_METHOD_NAME);
        }         
        
        //�y�[�W���\���s���`�F�b�N
        //�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
            log.error("�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //�y�[�W���\���s���`�F�b�N
        //�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B
        if (Long.parseLong(this.pageSize) <= 0)
        {
            log.error("�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME);
        } 
                      
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 418877BB02DE
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3BVSBookValueSpecsResponse(this);
    }
}
@
