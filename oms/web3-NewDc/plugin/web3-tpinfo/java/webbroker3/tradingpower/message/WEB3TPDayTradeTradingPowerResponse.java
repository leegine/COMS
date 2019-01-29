head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.32.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPDayTradeTradingPowerResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���v���������]�͎��Z��ʕ\�����X�|���X(WEB3TPDayTradeTradingPowerResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) �V�K�쐬
 */
package webbroker3.tradingpower.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (���v���������]�͎��Z��ʕ\�����X�|���X)<BR>
 * ���v���������]�͎��Z��ʕ\�����X�|���X�N���X�B<BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPDayTradeTradingPowerResponse extends WEB3GenResponse 
{
   /**
    * PTYPE
    */
   public static final String PTYPE = "tradingpower_daytrade_tradingpower";
   
   /**
    * �����R�[�h
    */
   public String productCode;
   
   /**
    * ������
    */
   public String productName;
   
   /**
    * �w��������t�]��
    */
   public String dayTradeEquityTradingPower;
   
   /**
    * �w��������t�\����
    */
   public String dayTradeEquitySellPossQuantity;
   
   
   /**
    * (�R���X�g���N�^)
    * @@param l_request
    * @@roseuid 41B69EEE0260
    */
   protected WEB3TPDayTradeTradingPowerResponse(WEB3GenRequest l_request) 
   {
        super( l_request );
   }
   
   /**
    * (�R���X�g���N�^)
    * @@roseuid 41B5544C03B1
    */
   public WEB3TPDayTradeTradingPowerResponse() 
   {
    
   }
   
}
@
