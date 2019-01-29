head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.34.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPDayTradeTradingPowerRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���v���������]�͎��Z���N�G�X�g�N���X(WEB3TPDayTradeTradingPowerRequest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) �V�K�쐬
 */
package webbroker3.tradingpower.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���v���������]�͎��Z��ʕ\�����N�G�X�g)<BR>
 * ���v���������]�͎��Z��ʕ\�����N�G�X�g�N���X�B<BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPDayTradeTradingPowerRequest extends WEB3GenRequest 
{
    
   /**
    * PTYPE
    */
   public static final String PTYPE = "tradingpower_daytrade_tradingpower";

   /**
     * ���O���[�e�B���e�B
     */
   private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPDayTradeTradingPowerRequest.class);

   /**
    * (�f�o�b�Oison)
    */
   private static boolean DBG = log.ison();

   /**
    * �����R�[�h
    */
   public String productCode;
   
   /**
    * (�R���X�g���N�^)
    * @@roseuid 41B5544C02A7
    */
   public WEB3TPDayTradeTradingPowerRequest() 
   {
   }
   
   /**
    * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
    * <BR>
    * @@return ���X�|���X�I�u�W�F�N�g
    * @@roseuid 41B5544C02D6
    */
   public WEB3GenResponse createResponse() 
   {
        return new WEB3TPDayTradeTradingPowerResponse(this);
   }
   
   /**
    * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
    * �i�������A���N���X���ŊȌ�����ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
    * <BR>
    * �P�j�@@�����R�[�h�`�F�b�N<BR>
    * <BR>
    * @@roseuid 41B68FAE01F3
    */
   protected void validate() throws WEB3BaseException 
   {
       log.debug("this.productCode = " + productCode);
       
       //�����R�[�hNULL�̏ꍇ�A�����I��
       if ( productCode == null )
       {
           if( DBG ) 
           {
               log.debug("�����R�[�h��NULL�ł��B");
           }
           return;
       }

       //�����R�[�h�󕶎��`�F�b�N
       if ( productCode.equals("") )
       {
           if( DBG ) 
           {
               log.debug("�����R�[�h���󕶎��ł��B");
           }
           throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                    getClass().getName() + ".validate");
       }

       //�����R�[�h���p�p�����`�F�b�N
       if ( !WEB3StringTypeUtility.isLetterOrDigit( productCode ) )
       {
           if( DBG ) 
           {
               log.debug("�����R�[�h�����p�p�����ł͂���܂���B");
           }
           throw new WEB3BusinessLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_00815,
                     getClass().getName() + ".validate");
       }

       //�����R�[�h�����`�F�b�N
       if ( WEB3StringTypeUtility.getByteLength( productCode ) != 5 )
       {
           if( DBG ) 
           {
               log.debug("�����R�[�h���T���łȂ��ł��B");
           }
           throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00439,
                    getClass().getName() + ".validate");
       }

    }
    
}
@
