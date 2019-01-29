head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.32.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPMarginTradingPowerDetailRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �M�p�V�K���]�͏ڍ׉�ʕ\�����N�G�X�g�N���X(WEB3TPMarginTradingPowerDetailRequest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) �V�K�쐬
 */
package webbroker3.tradingpower.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

import java.util.Date;

/**
 * (�M�p�V�K���]�͏ڍ׉�ʕ\�����N�G�X�g)<BR>
 * �M�p�V�K���]�͏ڍ׉�ʕ\�����N�G�X�g�N���X�B<BR>
 * 
 * @@author asano(SCS)
 */

public class WEB3TPMarginTradingPowerDetailRequest extends WEB3GenRequest 
{

   /**
    * PTYPE
    */
   public static final String PTYPE = "tradingpower_margin_tradingpower_detail";

   /**
    * ���O���[�e�B���e�B
    */
   private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPMarginTradingPowerDetailRequest.class);

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
    * @@roseuid 41B5838A026A
    */
   public WEB3TPMarginTradingPowerDetailRequest() 
   {
   }
   
   /**
    * (create���X�|���X)<BR>
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41B5838A0289
    */
   public WEB3GenResponse createResponse() 
   {
        return new WEB3TPMarginTradingPowerDetailResponse(this);
   }
   
   /**
    * (validate)<BR>
    * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
    * �i�������A���N���X���ŊȌ�����ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
    * <BR>
    * �P�j�@@�]�͌v�Z����ID�`�F�b�N<BR>
    * �Q�j�@@���t�`�F�b�N<BR>
    * <BR>
    * @@roseuid 41B6B44800F9
    */
   protected void validate() throws WEB3BusinessLayerException 
   {
   
       //�]�͌v�Z����IDNULL�`�F�b�N
       if ( bizDate == null )
       {
           if( DBG )
           {
               log.debug("�]�͌v�Z����ID��NULL�ł��B");
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
