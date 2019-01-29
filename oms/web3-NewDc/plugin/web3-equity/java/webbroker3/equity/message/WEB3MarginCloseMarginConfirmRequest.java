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
filename	WEB3MarginCloseMarginConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �M�p����ԍϒ����m�F���N�G�X�g�N���X(WEB3MarginCloseMarginConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/16 ������ (���u) �V�K�쐬
Revesion History : 2007/06/04 �����q (���u) �d�l�ύX���f��1153
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.define.WEB3MarginBeforeRequestDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�M�p����ԍϒ����m�F���N�G�X�g�j�B<br>
 * <br>
 * �M�p����ԍϒ����m�F���N�G�X�g�N���X
 * @@version 1.0
 */
public class WEB3MarginCloseMarginConfirmRequest extends WEB3MarginCommonRequest 
{      
   /**
    * Logger
    */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3MarginCloseMarginConfirmRequest.class);

    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_closeMarginConfirm";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200504201820L;
    
    /**
     * (���Ϗ����敪)<BR>
     * 0�F�����_���@@1�F�P���v���@@2�F�P�������@@3�F������<BR>
     * <BR>
     * �ꊇ���ς̏ꍇ�ݒ�<BR>
     */
    public String closingOrder;
    
    /**
     * (�M�p������ό������ׂ̈ꗗ)<BR>
     */
    public WEB3MarginCloseMarginContractUnit[] closeMarginContractUnits;
    
    /**
     * (�v�����敪)<BR>
     */
    public String requestFromType;

    /**
     * (�蓮�������σt���O)<BR>
     * �蓮�������σt���O<BR>
     * <BR>
     * true�F�@@�蓮�������ϒ���<BR>
     * false�F�@@�蓮�������ϒ����łȂ�<BR>
     * <BR>
     * @@return boolean
     */
    public boolean manualForcedSettleFlag = false;

    /**
     * @@roseuid 414047D80362
     */
    public WEB3MarginCloseMarginConfirmRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>                                                            
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>                                              
     * <BR>                                                                                                      
     * �P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo���B<BR>                                                    
     * <BR>                                                                                                      
     * �Q�j�@@���ό����ꗗ�`�F�b�N<BR>                                                                            
     * �@@�Q�|�P�jthis.���ό����ꗗ��null�̏ꍇ�A<BR>                                                             
     * �@@�@@�@@�@@�@@�u���ό����ꗗ��null�v�̗�O���X���[����B<BR>                                                  
     *         class: WEB3BusinessLayerException<BR>                                                             
     *         tag:   BUSINESS_ERROR_00610<BR>                                                                   
     * <BR>                                                                                                      
     * �@@�Q�|�Q�jthis.���ό����ꗗ���v�f����0�̏ꍇ�A<BR>                                                        
     * �@@�@@�@@�@@�@@�u���ό����ꗗ.�v�f����0�v�̗�O���X���[����B<BR>                                              
     *         class: WEB3BusinessLayerException<BR>                                                             
     *         tag:   BUSINESS_ERROR_00611<BR>                                                                   
     * <BR>                                                                                                      
     * �R�j�@@���Ϗ����敪�`�F�b�N<BR>                                                                            
     * �@@�R�|�P�jthis.���Ϗ����敪��null�ł��A<BR>                                                                     
     * �@@�@@�@@�@@�@@this.���Ϗ����敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>                                                             
     * �@@�@@�@@�@@�@@�u���Ϗ����敪������`�̒l�v�̗�O���X���[����B<BR>                                                    
     * �@@�@@�@@�@@�@@�@@�@@�E�h0�F�����_���h<BR>                                                                             
     * �@@�@@�@@�@@�@@�@@�@@�E�h1�F�P���v���h<BR>                                                                             
     * �@@�@@�@@�@@�@@�@@�@@�E�h2�F�P�������h<BR>                                                                             
     * �@@�@@�@@�@@�@@�@@�@@�E�h3�F�������h<BR>                                                                               
     *         class: WEB3BusinessLayerException<BR>                                                             
     *         tag:   BUSINESS_ERROR_00618<BR>                                                                   
     * <BR>                
     * �@@�R�|�Q�jthis.���Ϗ����敪��null���A<BR> 
     * �@@�@@�@@�@@�@@this.���ό����ꗗ�̗v�f�����P�̏ꍇ�A<BR> 
     * �@@�@@�@@�@@�@@�u�ꊇ�ԍώ��A���Ϗ��������w��v�̗�O���X���[����B<BR> 
     *   �@@�@@�@@class: WEB3BusinessLayerException<BR>
     *   �@@�@@�@@tag:   BUSINESS_ERROR_02304<BR>
     * <BR>
     * �S�j�@@���������`�F�b�N<BR>                                                                                
     * �@@�S�|�P�jthis.���Ϗ����敪��<BR>
     *       �inull�A�h1�F�P���v���h�A�h2�F�P�������h�A�h3�F�������h�̂����ꂩ�̒l�j�A<BR>
     * �@@�@@�@@�@@���@@this.����������null�̏ꍇ�A<BR>                                                             
     * �@@�@@�@@�@@�u����������null�v�̗�O���X���[����B<BR>                                                        
     *         class: WEB3BusinessLayerException<BR>                                                             
     *         tag:   BUSINESS_ERROR_00624<BR>                                                                   
     * <BR>                                                                                                      
     * �T�j�@@���Ϗ��ʃ`�F�b�N<BR>                                                                                
     * �@@�T�|�P�jthis.���Ϗ����敪���h0�F�����_���h�̏ꍇ�A<BR>                                                      
     * �@@�@@�@@�@@���ό����ꗗ�̗v�f(���ό�������)����   <BR>                                                           
     * �@@�@@�@@�@@���L�̃`�F�b�N���J��Ԃ��čs���B<BR>                                                              
     * �@@�@@�@@�@@�E���ό������ׂ�validate()���\�b�h���Ăяo���B<BR>                                                
     * <BR>                                                                                                      
     * �@@�T�|�Q�jthis.���Ϗ����敪���h0�F�����_���h�̏ꍇ�A <BR>                                                     
     * �@@�@@�@@�@@�@@�ȉ��̃`�F�b�N���s���B<BR>                                                                      
     * �@@�@@�T�|�Q�|�P�j���ό����ꗗ�̗v�f�����̑S�Ă̌��Ϗ��ʁ�null�܂���0�̏ꍇ�A<BR>                               
     * �@@�@@�@@�@@�u�v�f�����S�Ă̌��Ϗ��ʂ�null�܂���0�v�̗�O���X���[����B<BR>                                   
     *         class: WEB3BusinessLayerException<BR>                                                             
     *         tag:   BUSINESS_ERROR_00619<BR>                                                                   
     * <BR>                                                                                                      
     * �@@�@@�T�|�Q�|�Q�j���ό����ꗗ�̗v�f�����S�Ă̒��������A���Ϗ��ʂɂ��āA <BR>                                 
     * �@@�@@�@@�@@�������� > 0�@@���@@���Ϗ��� > 0�ƂȂ�g�ݍ��킹�����݂��Ȃ��ꍇ�A <BR>                               
     * �@@�@@�@@�@@�u���ϑΏۂȂ��v�̗�O���X���[����B<BR>                                                          
     *         class: WEB3BusinessLayerException<BR>                                                             
     *         tag:   BUSINESS_ERROR_00620<BR>                                                                   
     * <BR>                                                                                                      
     * �@@�T�|�R�jthis.���Ϗ����敪��null�܂��́A <BR>                                                                
     * �@@�@@�@@�@@�h1�F�P���v���h�A�h2�F�P�������h�A�h3�F�������h�̒l�̏ꍇ�A <BR>                                      
     * �@@�@@�@@�@@���ό����ꗗ�̗v�f(���ό�������)���� ���L�̃`�F�b�N���J��Ԃ��čs���B<BR>                         
     * �@@�@@�@@�E���ό�������.���Ϗ�����null�̏ꍇ�A <BR>                                                              
     * �@@�@@�@@�@@�u�����_���ԍψȊO�͒��������A���Ϗ��ʂ̎w��s�v�̗�O���X���[����B<BR>                        
     *         class: WEB3BusinessLayerException<BR>                                                             
     *         tag:   BUSINESS_ERROR_00621<BR>                                                                   
     * <BR>                                                                                                      
     * �@@�@@�@@�E���ό������ׂ�validate()���\�b�h���Ăяo���B<BR>                                                  
     * <BR>
     * �U�j�@@�v�����敪�`�F�b�N<BR>
     * �@@this.�v�����敪��null�ł��A<BR>
     * �@@this.�v�����敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�u�v�����敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h1�F�ԍό�]�͕\���h<BR>
     *         class: WEB3BusinessLayerException<BR>                                                             
     *         tag:   BUSINESS_ERROR_01983<BR>
     * <BR>
     * �V�j�@@�蓮�������σt���O�`�F�b�N<BR>
     * �@@�V�|�P�jthis.�蓮�������σt���O��true�A<BR>
     * �@@�@@�@@�@@�@@�����Ϗ����敪��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�蓮�������ς͈ꊇ���ϕs�v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02812<BR>
     * <BR>
     * �@@�V�|�Q�jthis.�蓮�������σt���O��true<BR>
     * �@@�@@�@@�@@�@@����this.���������敪��"��������"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�蓮�������ς͏o����܂Œ����w��s�v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02813<BR>
     * <BR>
     * �@@�V�|�R�jthis.�蓮�������σt���O��true<BR>
     * �@@�@@�@@�@@�@@����this.���������敪��"�w��Ȃ�"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�蓮�������ς͔��������w��s�v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02814<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 4084B4BE031A
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);
        super.validate();
        //* �Q�j�@@���ό����ꗗ�`�F�b�N<BR>                                                                            
        //     * �@@�Q�|�P�jthis.���ό����ꗗ��null�̏ꍇ�A<BR>                                                             
        //     * �@@�@@�@@�@@�@@�u���ό����ꗗ��null�v�̗�O���X���[����B<BR>                                                  
        //     *         class: WEB3BusinessLayerException<BR>                                                             
        //     *         tag:   BUSINESS_ERROR_00610<BR>                                                                   
        //  
        if (closeMarginContractUnits == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00610,STR_METHOD_NAME);
        }
        //* �@@�Q�|�Q�jthis.���ό����ꗗ���v�f����0�̏ꍇ�A<BR>                                                        
        //     * �@@�@@�@@�@@�@@�u���ό����ꗗ.�v�f����0�v�̗�O���X���[����B<BR>                                              
        //     *         class: WEB3BusinessLayerException<BR>                                                             
        //     *         tag:   BUSINESS_ERROR_00611<BR>                                                                   
        //      
        if (closeMarginContractUnits.length == 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00611,STR_METHOD_NAME);
        }
        //* �R�j�@@���Ϗ����敪�`�F�b�N<BR>                                                                            
        //     * �@@�R�|�P�jthis.���Ϗ����敪��null�ł��A<BR>                                                                     
        //     * �@@�@@�@@�@@�@@this.���Ϗ����敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>                                                             
        //     * �@@�@@�@@�@@�@@�u���Ϗ����敪������`�̒l�v�̗�O���X���[����B<BR>                                                    
        //     * �@@�@@�@@�@@�@@�@@�@@�E�h0�F�����_���h<BR>                                                                             
        //     * �@@�@@�@@�@@�@@�@@�@@�E�h1�F�P���v���h<BR>                                                                             
        //     * �@@�@@�@@�@@�@@�@@�@@�E�h2�F�P�������h<BR>                                                                             
        //     * �@@�@@�@@�@@�@@�@@�@@�E�h3�F�������h<BR>                                                                               
        //     *         class: WEB3BusinessLayerException<BR>                                                             
        //     *         tag:   BUSINESS_ERROR_00618<BR>                                                                   
        //                            
        if (closingOrder != null && !WEB3ClosingOrderDef.RANDOM.equals(closingOrder) && !WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(closingOrder)  && !WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(closingOrder) && !WEB3ClosingOrderDef.OPEN_DATE.equals(closingOrder))            
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00618,STR_METHOD_NAME);
        }
        //    * �@@�R�|�Q�jthis.���Ϗ����敪��null���A<BR> 
        //    * �@@�@@�@@�@@�@@this.���ό����ꗗ�̗v�f�����P�̏ꍇ�A<BR> 
        //    * �@@�@@�@@�@@�@@�u�ꊇ�ԍώ��A���Ϗ��������w��v�̗�O���X���[����B<BR> 
        //    *   class: WEB3BusinessLayerException<BR>
        //    *   tag:   BUSINESS_ERROR_02304<BR>
        //
        if (closingOrder == null && closeMarginContractUnits.length > 1)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02304,STR_METHOD_NAME);
        }
        //* �S�j�@@���������`�F�b�N<BR>                                                                                
        //    * �@@�S�|�P�jthis.���Ϗ����敪��<BR>
        //    *       �inull�A�h1�F�P���v���h�A�h2�F�P�������h�A�h3�F�������h�̂����ꂩ�̒l�j�A<BR>
        //    * �@@�@@�@@�@@���@@this.����������null�̏ꍇ�A<BR>                                                             
        //    * �@@�@@�@@�@@�u����������null�v�̗�O���X���[����B<BR>                                                        
        //    *         class: WEB3BusinessLayerException<BR>                                                             
        //    *         tag:   BUSINESS_ERROR_00624<BR>                                                                   
        //                                          
        if ((closingOrder == null || WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(closingOrder)  || WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(closingOrder) || WEB3ClosingOrderDef.OPEN_DATE.equals(closingOrder)) && orderQuantity == null) 
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00624,STR_METHOD_NAME);
        }
        //* �T�j�@@���Ϗ��ʃ`�F�b�N<BR>                                                                                
        //    * �@@�T�|�P�jthis.���Ϗ����敪���h0�F�����_���h�̏ꍇ�A<BR>                                                      
        //    * �@@�@@�@@�@@���ό����ꗗ�̗v�f(���ό�������)����   <BR>                                                           
        //    * �@@�@@�@@�@@���L�̃`�F�b�N���J��Ԃ��čs���B<BR>                                                              
        //    * �@@�@@�@@�@@�E���ό������ׂ�validate()���\�b�h���Ăяo���B<BR>                                                
        //      �@@�T�|�Q�jthis.���Ϗ����敪���h0�F�����_���h�̏ꍇ�A <BR>                                                     
        //     * �@@�@@�@@�@@�@@�ȉ��̃`�F�b�N���s���B<BR>                                                                      
        //     * �@@�@@�T�|�Q�|�P�j���ό����ꗗ�̗v�f�����̑S�Ă̌��Ϗ��ʁ�null�܂���0�̏ꍇ�A<BR>                               
        //     * �@@�@@�@@�@@�u�v�f�����S�Ă̌��Ϗ��ʂ�null�܂���0�v�̗�O���X���[����B<BR>                                   
        //     *         class: WEB3BusinessLayerException<BR>                                                             
        //     *         tag:   BUSINESS_ERROR_00619<BR>                                                                   
        //     * �@@�@@�T�|�Q�|�Q�j���ό����ꗗ�̗v�f�����S�Ă̒��������A���Ϗ��ʂɂ��āA <BR>                                 
        //     * �@@�@@�@@�@@�������� > 0�@@���@@���Ϗ��� > 0�ƂȂ�g�ݍ��킹�����݂��Ȃ��ꍇ�A <BR>                               
        //     * �@@�@@�@@�@@�u���ϑΏۂȂ��v�̗�O���X���[����B<BR>                                                          
        //     *         class: WEB3BusinessLayerException<BR>                                                             
        //     *         tag:   BUSINESS_ERROR_00620<BR>     
        //
        long l_lngnullCnt=0;
        boolean l_boolFlag=false;
        if ((WEB3ClosingOrderDef.RANDOM).equals(closingOrder))
        {
            for (int i = 0; i < closeMarginContractUnits.length;i++)
            {
                
                closeMarginContractUnits[i].validate();
                if (closeMarginContractUnits[i].settlePriority == null || WEB3ClosingOrderDef.RANDOM.equals(closeMarginContractUnits[i].settlePriority))
                {
                    l_lngnullCnt++;
                }

                if (closeMarginContractUnits[i].orderQuantity!= null 
                    && Long.parseLong(closeMarginContractUnits[i].orderQuantity) > 0 
                    && closeMarginContractUnits[i].settlePriority != null 
                    && Long.parseLong(closeMarginContractUnits[i].settlePriority) > 0)
                {
                    l_boolFlag=true;
                }
            }

            if (l_lngnullCnt >= closeMarginContractUnits.length)
            {
                
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00619,STR_METHOD_NAME);
            }
            if (!l_boolFlag)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00620,STR_METHOD_NAME);   
            }
        }
        //* �@@�T�|�R�jthis.���Ϗ����敪��null�܂��́A <BR>                                                                
        //    * �@@�@@�@@�@@�h1�F�P���v���h�A�h2�F�P�������h�A�h3�F�������h�̒l�̏ꍇ�A <BR>                                      
        //    * �@@�@@�@@�@@���ό����ꗗ�̗v�f(���ό�������)���� ���L�̃`�F�b�N���J��Ԃ��čs���B<BR>                         
        //    * �@@�@@�@@�E���ό�������.���Ϗ�����null�̏ꍇ�A <BR>                                                              
        //    * �@@�@@�@@�@@�u�����_���ԍψȊO�͒��������A���Ϗ��ʂ̎w��s�v�̗�O���X���[����B<BR>                        
        //    *         class: WEB3BusinessLayerException<BR>                                                             
        //    *         tag:   BUSINESS_ERROR_00621<BR>                                                                   
        //
        l_boolFlag=false;
        if (closingOrder == null || WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(closingOrder) || WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(closingOrder) || WEB3ClosingOrderDef.OPEN_DATE.equals(closingOrder)) 
        {
            for (int i = 0;i < closeMarginContractUnits.length;i++)
            {
                if (closeMarginContractUnits[i].settlePriority == null)
                {
                    l_boolFlag=true;
                }                 
            }
            if (!l_boolFlag)   
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00621,STR_METHOD_NAME);
            }
        }
        //* �@@�@@�@@�E���ό������ׂ�validate()���\�b�h���Ăяo���B<BR>                                           
        //
        for (int i = 0;i < closeMarginContractUnits.length;i++)
        {
            closeMarginContractUnits[i].validate();
        }
        
        if (requestFromType != null &&
            !WEB3MarginBeforeRequestDivDef.AFTER_REPAY.equals(requestFromType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01983,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // �V�|�P�jthis.�蓮�������σt���O��true�A
        // �����Ϗ����敪��null�̏ꍇ�A
        // �u�蓮�������ς͈ꊇ���ϕs�v�̗�O���X���[����B
        if (this.manualForcedSettleFlag && closingOrder != null)
        {
            log.debug("�蓮�������ς͈ꊇ���ϕs�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02812,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�蓮�������ς͈ꊇ���ϕs�B");
        }

        // �V�|�Q�jthis.�蓮�������σt���O��true
        // ����this.���������敪��"��������"�̏ꍇ�A
        if (this.manualForcedSettleFlag &&
            !WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(this.expirationDateType))
        {
            log.debug("�蓮�������ς͏o����܂Œ����w��s�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02813,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�蓮�������ς͏o����܂Œ����w��s�B");
        }

        // �V�|�R�jthis.�蓮�������σt���O��true
        // ����this.���������敪��"�w��Ȃ�"�̏ꍇ�A
        if (this.manualForcedSettleFlag &&
            !WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType))
        {
            log.debug("�蓮�������ς͔��������w��s�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02814,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�蓮�������ς͔��������w��s�B");
        }
    }
    
    /**
     * (validateAT���Ύ��)<BR>
     * ���Ύ���w�莞�́A�����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * �i�A�������p�̃��\�b�h�j<BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo���B<BR>
     * <BR>
     * �Q�j�@@���ό����ꗗ�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.���ό����ꗗ��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���ό����ꗗ��null�v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00610<BR>
     * <BR>
     * �@@�Q�|�Q�jthis.���ό����ꗗ���v�f����0�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���ό����ꗗ.�v�f����0�v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00611<BR>
     * <BR>
     * �R�j�@@���Ϗ����敪�`�F�b�N<BR>
     * �@@this.���Ϗ����敪��null�ł��A<BR>
     * �@@this.���Ϗ����敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�u���Ϗ����敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h0�F�����_���h<BR>
     * �@@�@@�@@�@@�E�h1�F�P���v���h<BR>
     * �@@�@@�@@�@@�E�h2�F�P�������h<BR>
     * �@@�@@�@@�@@�E�h3�F�������h<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00618<BR>
     * <BR>
     * �S�j�@@���������`�F�b�N<BR>
     * �@@�S�|�P�jthis.���Ϗ����敪���inull�A�h1�F�P���v���h�A�h2�F�P�������h�A�h3�F�������h�̂����ꂩ�̒l�j�A<BR>
     * �@@�@@�@@�@@���@@this.����������null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�u����������null�v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00624<BR>
     * <BR>
     * �T�j�@@���ׂ̒��������`�F�b�N<BR>
     * �@@�T�|�P�j���Ϗ����敪���h0�F�����_���h�̏ꍇ�̂݁A<BR>
     * �@@�@@�@@�@@���ό����ꗗ�̗v�f(���ό�������)����<BR>
     * �@@�@@�@@�@@���L�̃`�F�b�N���J��Ԃ��čs���B<BR>
     * �@@�@@�@@�@@�����Ύ���̏ꍇ�́A���ۂɂ͂P���ׂ݂̂��ݒ肳��Ă���B<BR>
     * �@@�@@�@@�@@���܂��A�����_���ȊO�̏ꍇ�́A���N�G�X�g.�����������g�p����̂ŁA<BR>
     * �@@�@@�@@�@@���`�F�b�N�s�v�B<BR>
     * �@@�@@�@@�@@�E���ό�������.�������� ���ȉ��̂����ꂩ�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u���ϖ��ׂ̒��������w�肪�s���v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�Enull <BR>
     * �@@�@@�@@�@@�@@�@@�E�����ȊO <BR>
     * �@@�@@�@@�@@�@@�@@�E�O�ȉ��̐��� <BR>
     * �@@�@@�@@�@@�@@�@@�E�W���𒴂��鐔�� <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02285<BR>
     * <BR>
     * �U�j�@@�v�����敪�`�F�b�N<BR>
     * �@@this.�v�����敪��null�ł��A<BR>
     * �@@this.�v�����敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�u�v�����敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h1�F�ԍό�]�͕\���h<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01983<BR>
     * @@throws WEB3BaseException
     */
    public void validateAtReverseOrder() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateAtReverseOrder()";
        log.entering(STR_METHOD_NAME);
        
        super.validate();
        
        if (closeMarginContractUnits == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00610,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (closeMarginContractUnits.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00611,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (closingOrder != null && !WEB3ClosingOrderDef.RANDOM.equals(closingOrder) && !WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(closingOrder)  && !WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(closingOrder) && !WEB3ClosingOrderDef.OPEN_DATE.equals(closingOrder))            
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00618,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if ((closingOrder == null || WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(closingOrder)  || WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(closingOrder) || WEB3ClosingOrderDef.OPEN_DATE.equals(closingOrder)) && orderQuantity == null) 
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00624,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (WEB3ClosingOrderDef.RANDOM.equals(closingOrder))
        {
            for (int i = 0;i < closeMarginContractUnits.length;i++)
            {
                String l_strOrderQuantity = closeMarginContractUnits[i].orderQuantity;
                if (l_strOrderQuantity == null)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02285,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                if (!WEB3StringTypeUtility.isNumber(l_strOrderQuantity))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02285,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                long l_lngOrderQuantity =
                    Long.parseLong(closeMarginContractUnits[i].orderQuantity);
                if (l_lngOrderQuantity <= 0L ||
                    l_lngOrderQuantity > 99999999L)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02285,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
        }
        
        if (requestFromType != null &&
            !WEB3MarginBeforeRequestDivDef.AFTER_REPAY.equals(requestFromType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01983,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }
    
    /**
         * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse() 
    {
     return new WEB3MarginCloseMarginConfirmResponse(this);
    }            
}
@
