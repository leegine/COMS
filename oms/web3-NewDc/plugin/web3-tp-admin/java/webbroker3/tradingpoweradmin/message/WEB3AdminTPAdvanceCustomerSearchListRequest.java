head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.40.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPAdvanceCustomerSearchListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3AdminTPAdvanceCustomerSearchListRequest.java
Author Name      : Daiwa Institute of Research
Revision History : 2005/02/08 asano(SCS) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �ۏ؋��ێ�������/���֋������ڋq�����ꗗ��ʕ\�����N�G�X�g
 */
public class WEB3AdminTPAdvanceCustomerSearchListRequest extends WEB3GenRequest
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "admintradingpower_advance_customer_search_list";
       
    /**
      * ���O���[�e�B���e�B
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTPAdvanceCustomerSearchListRequest.class);

    /**
     * (�f�o�b�Oison)
     */
    private static boolean DBG = log.ison();

    /**
     * (�l�􂢋敪)
     */
    public String markToMarketDiv;
    
    /**
     * (�ڋq����)
     */
    public String customerAttribute;
    
    /**
     * (��p�]���ቺ��)
     */
    public String substituteValuationDropRate;
    
    /**
     * (���X�R�[�h)
     */
    public String[] branchCode;
    
    /**
     * (�ڋq�R�[�h)
     */
    public String customerCode;
    
    /**
     * (�v���y�[�W�ԍ�)
     */
    public String pageIndex;

    /**
     * (�y�[�W���\���s��)
     */
    public String pageSize;

    /**
     * (�R���X�g���N�^)
     */
    public WEB3AdminTPAdvanceCustomerSearchListRequest()
    {
    }

    /**
     * (create���X�|���X)
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminTPAdvanceCustomerSearchListResponse();
    }
    
    /**
     * (validate)
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B 
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j 
     */
    public void validate() throws WEB3BaseException
    {

        //�l�􂢋敪�`�F�b�N(null�s��)
         if( markToMarketDiv == null ) 
         {
             if( DBG ) 
             {
                log.debug("�l�􂢋敪��NULL�ł��B");
             }
             throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        getClass().getName() + ".validate");            
         }
    
        //(�ڋq�����`�F�b�N(null�s��)
        if( customerAttribute == null )
        {
            if( DBG ) 
            {
                log.debug("�ڋq������NULL�ł��B");
            }
            throw new WEB3SystemLayerException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                       getClass().getName() + ".validate");                    
        }
    
        //��p�]���ቺ��
        if( substituteValuationDropRate != null && !substituteValuationDropRate.equals("") )
        {
            //�����`�F�b�N
            if( !WEB3StringTypeUtility.isNumber( substituteValuationDropRate ) )
            {
                if( DBG ) 
                {
                    log.debug("��p�]���ቺ���������łȂ��B");
                }
                throw new WEB3SystemLayerException(
                           WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                           getClass().getName() + ".validate");                
            } 
            //0<=��p�]���ቺ��<=20�܂ŉ�
            double l_intDropRate = Double.parseDouble( substituteValuationDropRate );
            if( l_intDropRate < 0.0d  || l_intDropRate > 20.0d)
            {
                if( DBG ) 
                {
                    log.debug("0<=��p�]���ቺ��<=20�łȂ��B");
                }
                throw new WEB3SystemLayerException(
                           WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                           getClass().getName() + ".validate");                
            } 
            //��p�]���ቺ���������_��2�ʂ܂ŉ�
            if( WEB3StringTypeUtility.getFractionDigits( substituteValuationDropRate ) > 2 )
            {
                if( DBG ) 
                {
                    log.debug("��p�]���ቺ���������_��2�ʂ܂łłȂ��B");
                }
                throw new WEB3SystemLayerException(
                           WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                           getClass().getName() + ".validate");                               
            }
            
        }
        
        //���X�R�[�h(null�s��)
        if( branchCode == null )
        {
            if( DBG ) 
            {
                log.debug("���X��NULL�ł��B");
            }
            throw new WEB3SystemLayerException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                       getClass().getName() + ".validate");                        
        }
        for(int i=0; i<branchCode.length; i++)
        {
            //null�s��
            if(  branchCode[ i ] == null )
            {
                if( DBG ) 
                {
                    log.debug("���X�R�[�h��NULL�ł��B");
                }
                throw new WEB3SystemLayerException(
                           WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                           getClass().getName() + ".validate");                                        
            }
            //�����̂݉�
            if( !WEB3StringTypeUtility.isDigit( branchCode[ i ] ) )
            {
                if( DBG ) 
                {
                    log.debug("���X�R�[�h�������łȂ��B");
                }
                throw new WEB3SystemLayerException(
                           WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                           getClass().getName() + ".validate");                
            }             
            //3���̂݉�
            if( !( branchCode[ i ].getBytes().length == 3 ) )
            {
                if( DBG ) 
                {
                    log.debug("���X��3���łȂ��B");
                }
                throw new WEB3SystemLayerException(
                           WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                           getClass().getName() + ".validate");                
            }             
        }
    
        //�ڋq�R�[�h
        if( customerCode != null && !customerCode.equals("") )
        {
            //�����̂݉�
            if( !WEB3StringTypeUtility.isDigit( customerCode ) )
            {
                if( DBG ) 
                {
                    log.debug("�ڋq�R�[�h�������łȂ��B");
                }
                throw new WEB3SystemLayerException(
                           WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                           getClass().getName() + ".validate");                
            } 
            //6���̂݉�
            if( !(WEB3StringTypeUtility.getNubmerLength( customerCode ) == 6) )
            {
                if( DBG ) 
                {
                    log.debug("�ڋq�R�[�h��6���łȂ��B");
                }
                throw new WEB3SystemLayerException(
                           WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                           getClass().getName() + ".validate");                
            }             
            
        }
    
        //�v���y�[�W�ԍ�(null�s��)
        if( pageIndex == null )
        {
            if( DBG ) 
            {
                log.debug("�v���y�[�W�ԍ���NULL�ł��B");
            }
            throw new WEB3SystemLayerException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                       getClass().getName() + ".validate");                                                
        }

        //�y�[�W���\���s��(null�s��)
        if( pageSize == null )
        {
            if( DBG ) 
            {
                log.debug("�y�[�W���\���s����NULL�ł��B");
            }
            throw new WEB3SystemLayerException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                       getClass().getName() + ".validate");                                                
            
        }

    }

}
@
