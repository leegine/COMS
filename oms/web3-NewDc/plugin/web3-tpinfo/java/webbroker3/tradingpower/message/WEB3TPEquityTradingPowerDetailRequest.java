head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.33.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPEquityTradingPowerDetailRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���������t�]�͏ڍ׉�ʕ\�����N�G�X�g�N���X(WEB3TPEquityTradingPowerDetailRequest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) �V�K�쐬
 */
package webbroker3.tradingpower.message;

import java.util.Date;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (���������t�]�͏ڍ׉�ʕ\�����N�G�X�g)<BR>
 * ���������t�]�͏ڍ׉�ʕ\�����N�G�X�g�N���X�B<BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPEquityTradingPowerDetailRequest extends WEB3GenRequest 
{

   /**
    * PTYPE
    */ 
   public static final String PTYPE = "tradingpower_equity_tradingpower_detail";

   /**
     * ���O���[�e�B���e�B
     */
   private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPEquityTradingPowerDetailRequest.class);

   /**
    * (�f�o�b�Oison)
    */
   private static boolean DBG = log.ison();

   /**
    * �]�͌v�Z����ID
    */
   public String calcResultId;
   
   /**
    * ���t
    */
   public Date bizDate;
   
   /**
    * (�R���X�g���N�^)<BR>
    * @@roseuid 41B56715023A
    */
   public WEB3TPEquityTradingPowerDetailRequest() 
   {
       super();
   }
   
   /**
    * (create���X�|���X)<BR>
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41B567150269
    */
   public WEB3GenResponse createResponse() 
   {
        return new WEB3TPEquityTradingPowerDetailResponse(this);
   }
   
   /**
    * (validate)<BR>
    * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
    * �i�������A���N���X���ŊȌ�����ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
    * <BR>
    * �P�j�@@�]�͌v�Z����ID�`�F�b�N<BR>
    * �Q�j�@@���t�`�F�b�N<BR>
    * <BR>
    * @@roseuid 41B68FD40260
    */
   protected void validate() throws WEB3BusinessLayerException 
   {
       
        //�]�͌v�Z����NULL�`�F�b�N
        if( calcResultId  == null )
        {
            if( DBG )
            {
                log.debug("�]�͌v�Z���ʂ�NULL�ł��B");
            }
            throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                       getClass().getName() + ".validate");        
        }
   
        //���tNULL�`�F�b�N
        if ( bizDate == null )
        {
            if( DBG )
            {            
                log.debug("���t��NULL�ł��B");
            }
            throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                       getClass().getName() + ".validate");
        }

   }
   
}
@
