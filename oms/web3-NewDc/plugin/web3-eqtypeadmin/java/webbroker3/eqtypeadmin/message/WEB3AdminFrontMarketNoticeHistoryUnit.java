head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontMarketNoticeHistoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �s��ʒm���𖾍� (WEB3AdminFrontMarketNoticeHistoryUnitt.java)
Author Name      : Daiwa Institute of Research
*/


package webbroker3.eqtypeadmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * �s��ʒm���𖾍׃N���X<BR>
 */
public class WEB3AdminFrontMarketNoticeHistoryUnit extends Message 
{
   
   /**
    * ������t�ԍ�<BR>
    */
   public String acceptNumber;
   
   /**
    * ���z�T�[�oNo<BR>
    */
   public String virtualServerNumber;
   
   /**
    * �Г���������<BR>
    */
   public String corpCode;
   
   /**
    * �d���ʔ�<BR>
    */
   public String noticeNumber;
   
   /**
    * �ʒm��M����<BR>
    */
   public Date createdTimestamp;
   
   /**
    * ���X�R�[�h<BR>
    */
   public String branchCode;
   
   /**
    * �����R�[�h<BR>
    */
   public String accountCode;
   
   /**
    * �����R�[�h<BR>
    */
   public String productCode;
   
   /**
    * �����敪<BR>
    */
   public String dealingType;
   
   /**
    * �f�[�^��ʃR�[�h<BR>
    */
   public String dataClassCode;
   
   /**
    * �f�[�^��ʏڍ׃R�[�h<BR>
    */
   public String dataClassDetailCode;
   
   /**
    * �đ��_�u���t���O<BR>
    */
   public String resendFlg;
   
   /**
    * ���s����<BR>
    */
   public String execCondType;
   
   /**
    * �l�i����<BR>
    */
   public String priceCondType;
   
   /**
    * ��������<BR>
    */
   public String orderQuantity;
   
   /**
    * �����P��<BR>
    */
   public String limitPrice;
   
   /**
    * �G���[�R�[�h<BR>
    */
   public String errorCode;
   
   /**
    * �M�p����敪<BR>
    */
   public String marginCode;
   
   /**
    * ���Ȉϑ��敪<BR>
    */
   public String tradeauditCode;
   
   /**
    * �󔄂�t���O<BR>
    */
   public String shortSellOrderFlag;
   
   /**
    * �폜����<BR>
    */
   public String cutQuantity;
   
   /**
    * �i������j��������<BR>
    */
   public String orgOrderQuantity;
   
   /**
    * �i������j�����P��<BR>
    */
   public String orgLimitPrice;
   
   /**
    * �i������j�Г���������<BR>
    */
   public String orgCorpCode;
   
   /**
    * �i������j���s����<BR>
    */
   public String orgExecCondType;
   
   /**
    * �i������j�l�i����<BR>
    */
   public String orgPriceCondType;
   
   /**
    * �폜�ϐ���<BR>
    */
   public String canceledQuantity;
   
   /**
    * ���o������<BR>
    */
   public String executedQuantity;
   
   /**
    * ���ʃR�[�h<BR>
    */
   public String modifiedResult;
   
   /**
    * �P������<BR>
    */
   public String priceMark;
   
   /**
    * ��萔��<BR>
    */
   public String execQuantity;
   
   /**
    * ���P��<BR>
    */
   public String execPrice;
   
   /**
    * �����c����<BR>
    */
   public String leftQuantity;
   
   /**
    * ���ʒm�ԍ�<BR>
    */
   public String execNumber;
   
   /**
    * �o������<BR>
    */
   public String execMark;
   
   /**
    * ������������<BR>
    */
   public String expirationQuantity;
   
   /**
    * �������R�R�[�h<BR>
    */
   public String reasonCode;
   
   /**
    * �X�g�b�v����<BR>
    */
   public String stopMark;
   
   /**
    * �R���X�g���N�^<BR>
    * @@roseuid 42D1F5960246
    */
   public WEB3AdminFrontMarketNoticeHistoryUnit() 
   {
    
   }
}
@
