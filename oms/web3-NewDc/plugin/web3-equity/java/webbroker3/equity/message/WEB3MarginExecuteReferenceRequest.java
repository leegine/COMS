head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginExecuteReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �M�p����������Ɖ�N�G�X�g�N���X(WEB3MarginExecuteReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/15 ������ (���u) �V�K�쐬
Revesion History : 2007/10/16 ����(���u) �d�l�ύX���f��1197
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderExecStatusDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.define.WEB3EquityReferenceTypeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�M�p����������Ɖ�N�G�X�g�j�B<br>
 * <br>
 * �M�p����������Ɖ�N�G�X�g�N���X
 * @@version 1.0
 */
public class WEB3MarginExecuteReferenceRequest extends WEB3GenRequest 
{
    /**
     * �iLogger�j�B
     */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3MarginExecuteReferenceRequest.class);
    /**
     * �iPTYPE�j�B
     */
    public static final String PTYPE = "margin_executeReference";

    /**
     * �iSerialVersionUID�j�B
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * �i�Ɖ�敪�j�B<BR>
     * <BR>
     * 0�F�������Ɖ�i�f�t�H���g�j<BR>
     * 1�F��������ꗗ�i��������\�Ȃ��̂̂ݕ\���j
     */
    public String referenceType;
    
    /**
     * �i�����R�[�h�j�B
     */
    public String productCode;
    
    /**
     * �i�s��R�[�h�j�B
     */
    public String marketCode;
    
    /**
     * �i����ԋ敪�j�B
     * <BR>
     * 0�F�����@@1�F�ꕔ�����@@2�F�S������
     */
    public String execType;
    
    /**
     * �i�������j�B<BR>
     * <BR>
     * ������
     */
    public Date orderBizDate;

    /**
     * (���������敪)<BR>
     * ���������敪<BR>
     * <BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l<BR>
     */
    public String orderCondType;
    
    /**
     * �i�M�p����\�[�g�L�[�j�B<BR>
     * <BR>
     * �Ώۍ��ځF�����R�[�h�A�����敪�A�s��R�[�h�A<BR>
     * ����敪�A�l�i�����A���s�����A���������A�������ԁA<BR>
     * �������A���������A�ٍϋ敪�A�ٍϊ����l
     */
    public WEB3MarginSortKey[] sortKeys;
    
    /**
     * �i�v���y�[�W�ԍ��j�B<BR>
     * <BR>
     * �\�����������y�[�W�ʒu���w��@@���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex;
    
    /**
     * �i�y�[�W���\���s���j�B<BR>
     * <BR>
     * �P�y�[�W���ɕ\�����������s�����w��<BR>
     */
    public String pageSize;
    
    /**
     * @@roseuid 414048CA01BC
     */
    public WEB3MarginExecuteReferenceRequest() 
    {
     
    }
    
    /**
     * �ivalidate�j�B<BR>
     * <BR>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>                                                                   
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>                                                     
     * <BR>                                                                                                             
     * �P�j�@@�Ɖ�敪�`�F�b�N<BR>                                                                                       
     * �@@�P�|�P�jthis.�Ɖ�敪��null�ł������ꍇ�A�u�Ɖ�敪��null�v�̗�O���X���[����B<BR>                            
     *         class: WEB3BusinessLayerException<BR>                                                                    
     *         tag:   BUSINESS_ERROR_00081<BR>                                                                          
     * <BR>                                                                                                             
     * �@@�P�|�Q�jthis.�Ɖ�敪�����L�̒l�ȊO���ݒ肳��Ă�����A�u�Ɖ�敪������`�̒l�v�̗�O���X���[����B<BR>        
     * �@@�@@�@@�@@�E�u0�F�������Ɖ�v<BR>                                                                                
     * �@@�@@�@@�@@�E�u1�F��������ꗗ(��������\�Ȃ��̂̂ݕ\��)�v<BR>                                                    
     *         class: WEB3BusinessLayerException<BR>                                                                    
     *         tag:   BUSINESS_ERROR_00082<BR>                                                                          
     * <BR>                                                                                                             
     * �Q�j�@@����ԋ敪�`�F�b�N<BR>                                                                                   
     * �@@�Q�|�P�jthis.����ԋ敪��null ���A<BR>                                                                     
     * �@@�@@�@@�@@this.����ԋ敪�����L�̒l�ȊO���ݒ肳��Ă�����A�u����ԋ敪������`�̒l�v�̗�O���X���[����B<BR>  
     * �@@�@@�@@�@@�E�u0�F�����v<BR>                                                                                      
     * �@@�@@�@@�@@�E�u1�F�ꕔ�����v<BR>                                                                                    
     * �@@�@@�@@�@@�E�u2�F�S�������v<BR>                                                                                                                                                                       
     *         class: WEB3BusinessLayerException<BR>                                                                    
     *         tag:   BUSINESS_ERROR_00626<BR>                                                                          
     * <BR>                                                                                                             
     * �R�j�@@�\�[�g�L�[�`�F�b�N<BR>                                                                                     
     * �@@�R�|�P�jthis.�\�[�g�L�[��null�ł������ꍇ�A�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B<BR>                      
     *         class: WEB3BusinessLayerException<BR>                                                                    
     *         tag:   BUSINESS_ERROR_00231<BR>                                                                          
     * <BR>                                                                                                             
     * �@@�R�|�Q�jthis.�\�[�g�L�[.�v�f�����O�������ꍇ�A�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B<BR>                 
     *         class: WEB3BusinessLayerException<BR>                                                                    
     *         tag:   BUSINESS_ERROR_00232<BR>                                                                          
     * <BR>                                                                                                             
     * �@@�R�|�R�jthis.�\�[�g�L�[�̑S�v�f�ɑ΂��ā@@���L�̃`�F�b�N���s���B<BR>                                            
     * �@@�@@�R�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B<BR>                                                          
     * <BR>                                                                                                             
     * �@@�@@�R�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂɉ��L�̍��ڈȊO���@@�ݒ肳��Ă�����A                                        
     * �@@�@@�@@�@@�@@�@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B<BR>                                        
     * �@@�@@�@@�@@�@@�@@�@@�E�u�����R�[�h�v<BR>                                                                               
     * �@@�@@�@@�@@�@@�@@�@@�E�u�����敪�v<BR>                                                                                 
     * �@@�@@�@@�@@�@@�@@�@@�E�u�s��R�[�h�v<BR>                                                                               
     * �@@�@@�@@�@@�@@�@@�@@�E�u����敪�v<BR>
     *              �E�u�l�i�����v<BR>                                                                                  
     * �@@�@@�@@�@@�@@�@@�@@�E�u���s�����v<BR>                                                                                 
     * �@@�@@�@@�@@�@@�@@�@@�E�u���������v<BR>                                                                                 
     * �@@�@@�@@�@@�@@�@@�@@�E�u�������ԁv<BR>
     *              �E�u�������v<BR>                                                                                 
     * �@@�@@�@@�@@�@@�@@�@@�E�u���������v<BR>                                                                                 
     * �@@�@@�@@�@@�@@�@@�@@�E�u�ٍϋ敪�v<BR>                                                                                 
     * �@@�@@�@@�@@�@@�@@�@@�E�u�ٍϊ����l�v<BR>                                                                               
     *         class: WEB3BusinessLayerException<BR>                                                                    
     *         tag:   BUSINESS_ERROR_00086<BR>                                                                          
     * <BR>                                                                                                             
     * �S�j�v���y�[�W�ԍ��`�F�b�N<BR>                                                                                   
     * �@@�S�|�P�jthis.�v���y�[�W�ԍ���null�ł������ꍇ�A<BR>
     *        �u�v���y�[�W�ԍ���null�v�̗�O���X���[����B<BR>                
     *         class: WEB3BusinessLayerException<BR>                                                                    
     *         tag:   BUSINESS_ERROR_00089<BR>                                                                          
     * <BR>                                                                                                             
     * �@@�S�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A<BR>
     *         �u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B<BR>    
     *         class: WEB3BusinessLayerException<BR>                                                                    
     *         tag:   BUSINESS_ERROR_00090<BR>                                                                          
     * <BR>                                                                                                             
     * �@@�S�|�R�jthis.�v���y�[�W�ԍ����O�ł������ꍇ�A<BR>
     *         �u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B<BR>                 
     *         class: WEB3BusinessLayerException<BR>                                                                    
     *         tag:   BUSINESS_ERROR_00616<BR>                                                                          
     * <BR>                                                                                                             
     * �T�j�y�[�W���\���s���`�F�b�N<BR>                                                                                 
     * �@@�T�|�P�jthis.�y�[�W���\���s����null�ł������ꍇ�A<BR>
     *         �u�y�[�W���\���s����null�v�̗�O���X���[����B<BR>            
     *         class: WEB3BusinessLayerException<BR>                                                                    
     *         tag:   BUSINESS_ERROR_00091<BR>                                                                          
     * <BR>                                                                                                             
     * �@@�T�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A<BR>
     *         �u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>                                                                    
     *         tag:   BUSINESS_ERROR_00092<BR>                                                                          
     * �@@<BR>                                                                                                           
     * �@@�T�|�R�jthis.�y�[�W���\���s�����O�ł������ꍇ�A
     *        �u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B<BR>             
     *         class: WEB3BusinessLayerException<BR>                                                                    
     *         tag:   BUSINESS_ERROR_00617<BR> 
     * <BR>
     *   �U�j�@@�s��R�[�h�`�F�b�N 
     *        this.�s��R�[�h��null�A�����L�̒l�ȊO�̏ꍇ�A<BR>
     *        �u�s��R�[�h������`�̒l�v�̗�O���X���[����B
     * �@@�@@�@@�@@�@@�E�P�F���� 
     * �@@�@@�@@�@@�@@�E�Q�F��� 
     * �@@�@@�@@�@@�@@�E�R�F���É� 
     * �@@�@@�@@�@@�@@�E�U�F���� 
     * �@@�@@�@@�@@�@@�E�W�F�D�y 
     * �@@�@@�@@�@@�@@�E�X�FNNM 
     *          �E�P�O�FJASDAQ
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00608<BR>
     * <BR>
     * �V�j�@@���������敪�`�F�b�N<BR>
     * �@@this.���������敪��null�A<BR>
     * �@@�����L�̒l�ȊO�̏ꍇ�A<BR>
     *�@@�u���������敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�E�O�F�w��Ȃ�<BR>
     * �@@�@@�E�P�F�t�w�l<BR>
     * �@@�@@�E�Q�FW�w�l<BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00212<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 40866C06027E
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�@@�Ɖ�敪�`�F�b�N
        if (referenceType == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00081,STR_METHOD_NAME);
        }

        if (!WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(referenceType) && !WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(referenceType))
        {
            throw new  WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00082,STR_METHOD_NAME); 
        }
        
        // �Q�j�@@����ԋ敪�`�F�b�N            
        if ((execType != null) &&
            !(WEB3OrderExecStatusDef.EXECUTED.equals(execType) || WEB3OrderExecStatusDef.PARTIALLY_EXECUTED.equals(execType)
            || WEB3OrderExecStatusDef.UNEXECUTED.equals(execType)))
        {
            throw new  WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00626,STR_METHOD_NAME);            
        }
        
        // �R�j�@@�\�[�g�L�[�`�F�b�N
        if (sortKeys == null)
        {
            throw new  WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00231,STR_METHOD_NAME);
        }

        if (sortKeys.length == 0)
        {
            throw new  WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00232,STR_METHOD_NAME);
        }                                        
       
         for (int i = 0;i < sortKeys.length;i++)
         {
    
            sortKeys[i].validate();
            
            if (!WEB3EquityKeyItemDef.PRODUCTCODE.equals(sortKeys[i].keyItem) && !WEB3EquityKeyItemDef.ACCOUNTTYPE.equals(sortKeys[i].keyItem) 
                && !WEB3EquityKeyItemDef.TRADEMARKET.equals(sortKeys[i].keyItem) && !WEB3EquityKeyItemDef.TRADETYPE.equals(sortKeys[i].keyItem) 
                && !WEB3EquityKeyItemDef.EXECUTE_COND.equals(sortKeys[i].keyItem) && !WEB3EquityKeyItemDef.SEND_COND.equals(sortKeys[i].keyItem) 
                && !WEB3EquityKeyItemDef.ORDER_TIME.equals(sortKeys[i].keyItem) && !WEB3EquityKeyItemDef.ORDER_TIMELIMIT.equals(sortKeys[i].keyItem) 
                && !WEB3EquityKeyItemDef.REPAYMENT_DIV.equals(sortKeys[i].keyItem) && !WEB3EquityKeyItemDef.REPAYMENTNUM.equals(sortKeys[i].keyItem)
			    && !WEB3EquityKeyItemDef.PRICE_COND.equals(sortKeys[i].keyItem) && !WEB3EquityKeyItemDef.SEND_DATE.equals(sortKeys[i].keyItem))
            {
                
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00086,STR_METHOD_NAME);
            }    
         } 

         // �S�j�v���y�[�W�ԍ��`�F�b�N                                                               
         if (pageIndex == null)
         {
            
            throw new  WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00089,STR_METHOD_NAME);
            
         }

         if (!WEB3StringTypeUtility.isNumber(pageIndex))
         {
            throw new  WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00090,STR_METHOD_NAME);
         }
    
         long l_longTemp= Long.parseLong(pageIndex);
         if (l_longTemp <= 0)
         {
            throw new  WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00616,STR_METHOD_NAME);
         }

         if (pageSize == null)
         {
            throw new  WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00091,STR_METHOD_NAME);
         }

         if (!WEB3StringTypeUtility.isNumber(pageSize))
         {
            throw new  WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00092,STR_METHOD_NAME);
         }
         if (Long.parseLong(pageSize) <= 0)
         {
            throw new  WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00617,STR_METHOD_NAME);     
         }   
 
		//  �U�j�@@�s��w��`�F�b�N
		
		if (this.marketCode != null
				&& !WEB3MarketCodeDef.TOKYO.equals(this.marketCode)
				&& !WEB3MarketCodeDef.OSAKA.equals(this.marketCode)
				&& !WEB3MarketCodeDef.NAGOYA.equals(this.marketCode)
				&& !WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode)
				&& !WEB3MarketCodeDef.SAPPORO.equals(this.marketCode)
				&& !WEB3MarketCodeDef.NNM.equals(this.marketCode)
				&& !WEB3MarketCodeDef.JASDAQ.equals(this.marketCode))
		{
			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00608,STR_METHOD_NAME);
		}

        // �V�j�@@���������敪�`�F�b�N
        // this.���������敪��null�A�����L�̒l�ȊO�̏ꍇ�A�u���������敪������`�̒l�v�̗�O���X���[����B
        if (this.orderCondType != null)
        {
            if (!(WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType)
                || WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
                || WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)))
            {
                log.debug("���������敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00212,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���������敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }

        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 414048CA01E4
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MarginExecuteReferenceResponse(this);
    }
}
@
