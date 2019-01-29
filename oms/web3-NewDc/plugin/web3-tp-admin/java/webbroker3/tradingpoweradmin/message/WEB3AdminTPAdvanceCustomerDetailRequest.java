head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.41.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPAdvanceCustomerDetailRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3AdminTPAdvanceCustomerDetailRequest.java
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
 * �ۏ؋��ێ�������/���֋������ڋq�ڍ׉�ʕ\�����N�G�X�g
 */
public class WEB3AdminTPAdvanceCustomerDetailRequest extends WEB3GenRequest
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "admintradingpower_advance_customer_detail";

    /**
      * ���O���[�e�B���e�B
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTPAdvanceCustomerDetailRequest.class);

    /**
     * (�f�o�b�Oison)
     */
    private static boolean DBG = log.ison();

    /**
     * (�ڋq����)
     */
    public String customerAttribute;

    /**
     * (�]�͌v�Z����ID)
     */
    public String calcResultId;
       
    /**
     * (��p�]���ቺ��)
     */
    public String substituteValuationDropRate;

    /**
     * �R���X�g���N�^
     */
    public WEB3AdminTPAdvanceCustomerDetailRequest()
    {
    }

    /**
     * (create���X�|���X)
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminTPAdvanceCustomerDetailResponse();
    }
    
    /**
     * (validate)
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B 
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j 
     */
    public void validate() throws WEB3BaseException
    {
        
        //�ڋq�����`�F�b�N(null�s��)
        if ( customerAttribute == null )
        {
            if( DBG )
            {
                log.debug("�ڋq������NULL�ł��B");
            }
            throw new WEB3SystemLayerException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                       getClass().getName() + ".validate");
        }
        
        //�]�͌v�Z����ID�`�F�b�N(null�s��)
        if( calcResultId == null )
        {
            if( DBG )
            {
                log.debug("�ڋq������NULL�ł��B");
            }
            throw new WEB3SystemLayerException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                       getClass().getName() + ".validate");
        }
       
        //��p�]���ቺ���`�F�b�N
        if( substituteValuationDropRate != null && !substituteValuationDropRate.equals("") )
        {
            //�����̂�
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
            if( l_intDropRate < 0d  || l_intDropRate > 20)
            {
                if( DBG )
                {
                    log.debug("��p�]���ቺ���������łȂ��B");
                }
                throw new WEB3SystemLayerException(
                           WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                           getClass().getName() + ".validate");                
            } 
            //�����_��2�ʂ܂ŉ�
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

    }

}
@
