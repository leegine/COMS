head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.33.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ����]�̓��j�b�g(WEB3TPTradingPowerUnit.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/03/03 asano(SCS) �V�K�쐬
 */
package webbroker3.tradingpower.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (����]�̓��j�b�g)<BR>
 * ����]�̓��j�b�g�N���X�B<BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPTradingPowerUnit extends Message 
{
    
   /**
    * ���t
    */
   public Date bizDate;
       
   /**
    * ���������t�]��
    */
   public String equityTradingPower;
   
   /**
    * �M�p�V�K���]��
    */
   public String marginTradingPower;
   
   /**
    * �M�p�����]��
    */
   public String swapLongTradingPower;
   
   /**
    * ���M���t�]��
    */
   public String mutualTradingPower;
   
   /**
    * ���̑����i���t�]��
    */
   public String otherTradingPower;
   
   /**
    * �o���]��
    */
   public String paymentTradingPower;
   
   /**
    * �ۏ؋��a����
    */
   public String marginCollateralRate;
   
   /**
    * �]�͓K�p��<���������t�]��>
    */
   public Date equityBuyApplyDate;
   
   /**
    * �]�͓K�p��<�M�p�V�K���]��>
    */
   public Date marginApplyDate;

      
}
@
